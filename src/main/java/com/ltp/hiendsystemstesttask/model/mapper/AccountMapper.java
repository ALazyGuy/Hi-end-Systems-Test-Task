package com.ltp.hiendsystemstesttask.model.mapper;

import com.ltp.hiendsystemstesttask.model.dto.AccountInfo;
import com.ltp.hiendsystemstesttask.model.entity.AccountEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {

    public static AccountInfo toAccountInfo(final AccountEntity accountEntity) {
        if(Objects.isNull(accountEntity)) {
            return null;
        }
        return new AccountInfo(accountEntity.getMoney(), accountEntity.isActive());
    }

}
