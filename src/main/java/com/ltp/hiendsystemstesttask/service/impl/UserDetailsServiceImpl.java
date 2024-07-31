package com.ltp.hiendsystemstesttask.service.impl;

import com.ltp.hiendsystemstesttask.model.dto.UserDetailsImpl;
import com.ltp.hiendsystemstesttask.model.entity.UserEntity;
import com.ltp.hiendsystemstesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
