package com.ltp.hiendsystemstesttask.service;

import com.ltp.hiendsystemstesttask.exception.AccountActionException;
import com.ltp.hiendsystemstesttask.model.entity.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    AccountEntity deposit(final AccountEntity accountEntity, final int amount) throws AccountActionException;
    AccountEntity withdraw(final AccountEntity accountEntity, final int amount) throws AccountActionException;
}
