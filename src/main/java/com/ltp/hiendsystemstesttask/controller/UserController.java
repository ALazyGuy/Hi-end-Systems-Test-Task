package com.ltp.hiendsystemstesttask.controller;

import com.ltp.hiendsystemstesttask.model.dto.AccountActionRequest;
import com.ltp.hiendsystemstesttask.model.dto.UserInfo;
import com.ltp.hiendsystemstesttask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCurrentUserInfo() {
        final Optional<UserInfo> userInfo = userService.getUserInfo();

        if (userInfo.isPresent()) {
            return ResponseEntity.ok(userInfo.get());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/deposit")
    public ResponseEntity deposit(@Validated @RequestBody AccountActionRequest accountActionRequest) {
        final String errors = userService.depositMoney(accountActionRequest);
        if(errors.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(errors);
    }

    @PutMapping("/withdraw")
    public ResponseEntity withdraw(@Validated @RequestBody AccountActionRequest accountActionRequest) {
        final String errors = userService.withdrawMoney(accountActionRequest);
        if(errors.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(errors);
    }

}
