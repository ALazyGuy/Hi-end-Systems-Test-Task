package com.ltp.hiendsystemstesttask.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ltp.hiendsystemstesttask.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("token");

        if(Objects.nonNull(header) && header.startsWith("Bearer: ")){
            final String token = header.substring(8);
            final Optional<DecodedJWT> decodedJWTOptional = jwtUtils.verifyAndDecode(token);
            if(decodedJWTOptional.isPresent()) {
                final DecodedJWT decodedJWT = decodedJWTOptional.get();
                final String username = decodedJWT.getSubject();
                final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                final Authentication authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
