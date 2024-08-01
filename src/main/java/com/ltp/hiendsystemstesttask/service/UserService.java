package com.ltp.hiendsystemstesttask.service;

import com.ltp.hiendsystemstesttask.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<JwtResponse> registerUser(final UserRegisterRequest userRegisterRequest);
    Optional<JwtResponse> loginUser(final UserLoginRequest userLoginRequest);
    Optional<UserInfo> getUserInfo();
    String withdrawMoney(final AccountActionRequest accountActionRequest);
    String depositMoney(final AccountActionRequest accountActionRequest);
    List<UserInfo> getAllUsersInfo();
    void changeUserAccountStatus(final ChangeAccountStatusRequest changeAccountStatusRequest);
}
