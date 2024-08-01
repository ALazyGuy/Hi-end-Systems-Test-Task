package com.ltp.hiendsystemstesttask.model.mapper;

import com.ltp.hiendsystemstesttask.model.dto.UserInfo;
import com.ltp.hiendsystemstesttask.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserInfo entityToUserInfo(final UserEntity userEntity) {
        if(Objects.isNull(userEntity)){
            return null;
        }
        return new UserInfo(userEntity.getUsername(), userEntity.getUserRole().isAdmin());
    }

}
