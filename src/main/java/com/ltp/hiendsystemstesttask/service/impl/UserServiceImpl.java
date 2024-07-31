package com.ltp.hiendsystemstesttask.service.impl;

import com.ltp.hiendsystemstesttask.model.dto.JwtResponse;
import com.ltp.hiendsystemstesttask.model.dto.UserRegisterRequest;
import com.ltp.hiendsystemstesttask.model.entity.UserEntity;
import com.ltp.hiendsystemstesttask.model.entity.UserRole;
import com.ltp.hiendsystemstesttask.repository.UserRepository;
import com.ltp.hiendsystemstesttask.service.UserService;
import com.ltp.hiendsystemstesttask.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<JwtResponse> registerUser(final UserRegisterRequest userRegisterRequest) {
        if(userRepository.existsByUsername(userRegisterRequest.getUsername())) {
            return Optional.empty();
        }

        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRegisterRequest.getUsername());
        final String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        userEntity.setPassHash(encodedPassword);
        userEntity.setUserRole(UserRole.ROLE_USER);
        userRepository.save(userEntity);

        final String token = jwtUtils.generateToken(userRegisterRequest.getUsername());
        final JwtResponse response = new JwtResponse(token);
        return Optional.of(response);
    }
}
