package com.ltp.hiendsystemstesttask.controller;

import com.ltp.hiendsystemstesttask.model.dto.JwtResponse;
import com.ltp.hiendsystemstesttask.model.dto.UserLoginRequest;
import com.ltp.hiendsystemstesttask.model.dto.UserRegisterRequest;
import com.ltp.hiendsystemstesttask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.POST})
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@Validated @RequestBody UserRegisterRequest userRegisterRequest) {
        final Optional<JwtResponse> response = userService.registerUser(userRegisterRequest);

        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }

        return ResponseEntity.ok(response.get());
    }

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@Validated @RequestBody UserLoginRequest userLoginRequest) {
        final Optional<JwtResponse> response = userService.loginUser(userLoginRequest);

        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }

        return ResponseEntity.ok(response.get());
    }

}
