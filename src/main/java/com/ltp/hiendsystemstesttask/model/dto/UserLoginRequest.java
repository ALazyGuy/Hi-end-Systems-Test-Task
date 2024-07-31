package com.ltp.hiendsystemstesttask.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginRequest {
    @Size.List({
            @Size(min = 3, message = "Username length cannot be less than 3 characters"),
            @Size(max = 20, message = "Username length cannot be more than 20 characters")
    })
    @Pattern(regexp = "^([a-zA-Z]|\\d)+$",
            message = "Only letters, numbers and spaces should be used for username")
    private String username;
    @Pattern(regexp = "^([a-zA-Z]|\\d|\\s|(\\$|\\*|_))*$",
            message = "Only letters, spaces, numbers and special characters('$', '*') allowed for password")
    @Size(min = 4, message = "Minimum password length is 4")
    private String password;
}
