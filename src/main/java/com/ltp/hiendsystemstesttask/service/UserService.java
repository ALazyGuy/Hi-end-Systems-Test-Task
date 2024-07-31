package com.ltp.hiendsystemstesttask.service;

import com.ltp.hiendsystemstesttask.model.dto.JwtResponse;
import com.ltp.hiendsystemstesttask.model.dto.UserRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<JwtResponse> registerUser(final UserRegisterRequest userRegisterRequest);
}
