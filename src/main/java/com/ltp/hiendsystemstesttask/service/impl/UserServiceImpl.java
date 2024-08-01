package com.ltp.hiendsystemstesttask.service.impl;

import com.ltp.hiendsystemstesttask.exception.AccountActionException;
import com.ltp.hiendsystemstesttask.model.dto.*;
import com.ltp.hiendsystemstesttask.model.entity.AccountEntity;
import com.ltp.hiendsystemstesttask.model.entity.UserEntity;
import com.ltp.hiendsystemstesttask.model.entity.UserRole;
import com.ltp.hiendsystemstesttask.model.mapper.UserMapper;
import com.ltp.hiendsystemstesttask.repository.UserRepository;
import com.ltp.hiendsystemstesttask.service.AccountService;
import com.ltp.hiendsystemstesttask.service.UserService;
import com.ltp.hiendsystemstesttask.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

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

        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserEntity(userEntity);
        accountEntity.setMoney(0);
        accountEntity.setActive(true);
        userEntity.setAccount(accountEntity);

        userRepository.save(userEntity);

        final String token = jwtUtils.generateToken(userRegisterRequest.getUsername());
        final JwtResponse response = new JwtResponse(token);
        return Optional.of(response);
    }

    @Override
    public Optional<JwtResponse> loginUser(final UserLoginRequest userLoginRequest) {
        final Optional<UserEntity> userEntityOptional =
                userRepository.findByUsername(userLoginRequest.getUsername());

        if(userEntityOptional.isEmpty()) {
            return Optional.empty();
        }

        final UserEntity userEntity = userEntityOptional.get();
        if(!passwordEncoder.matches(userLoginRequest.getPassword(), userEntity.getPassHash())) {
            return Optional.empty();
        }

        final String token = jwtUtils.generateToken(userLoginRequest.getUsername());
        final JwtResponse response = new JwtResponse(token);
        return Optional.of(response);
    }

    @Override
    public Optional<UserInfo> getUserInfo() {
        return getAuthenticatedUser().map(UserMapper::entityToUserInfo);
    }

    @Override
    public String withdrawMoney(final AccountActionRequest accountActionRequest) {
        final Optional<UserEntity> userEntityOptional = getAuthenticatedUser();
        if(userEntityOptional.isPresent()) {
            try{
                final UserEntity userEntity = userEntityOptional.get();
                userEntity.setAccount(accountService
                        .withdraw(userEntity.getAccount(), accountActionRequest.getAmount()));
                userRepository.save(userEntity);
            }catch(AccountActionException e) {
                return e.getErrorMessage();
            }
        }
        return new String();
    }

    @Override
    public String depositMoney(final AccountActionRequest accountActionRequest) {
        final Optional<UserEntity> userEntityOptional = getAuthenticatedUser();
        if(userEntityOptional.isPresent()) {
            try{
                final UserEntity userEntity = userEntityOptional.get();
                userEntity.setAccount(accountService
                        .deposit(userEntity.getAccount(), accountActionRequest.getAmount()));
                userRepository.save(userEntity);
            }catch(AccountActionException e) {
                return e.getErrorMessage();
            }
        }
        return new String();
    }

    private Optional<UserEntity> getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.nonNull(authentication)) {
            final String username = authentication.getName();
            final Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            return userEntity;
        }

        return Optional.empty();
    }

    @Override
    public List<UserInfo> getAllUsersInfo() {
        final List<UserEntity> users = userRepository.findAllByUserRole(UserRole.ROLE_USER);
        return users.stream()
                .map(UserMapper::entityToUserInfo)
                .collect(Collectors.toList());
    }
}
