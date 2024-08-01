package com.ltp.hiendsystemstesttask.service.impl;

import com.ltp.hiendsystemstesttask.exception.AccountActionException;
import com.ltp.hiendsystemstesttask.model.entity.AccountEntity;
import com.ltp.hiendsystemstesttask.repository.AccountRepository;
import com.ltp.hiendsystemstesttask.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountEntity deposit(final AccountEntity accountEntity, final int amount)
            throws AccountActionException {
        if(!accountEntity.isActive()) {
            throw new AccountActionException("Account is locked by administrator");
        }

        accountEntity.setMoney(accountEntity.getMoney() + amount);
        accountRepository.save(accountEntity);
        return accountEntity;
    }

    @Override
    public AccountEntity withdraw(AccountEntity accountEntity, int amount) throws AccountActionException {
        if(!accountEntity.isActive()) {
            throw new AccountActionException("Account is locked by administrator");
        }

        final int newMoneyValue = accountEntity.getMoney() - amount;
        if(newMoneyValue < 0) {
            throw new AccountActionException("Not enough credits for withdraw");
        }

        accountEntity.setMoney(newMoneyValue);
        accountRepository.save(accountEntity);
        return accountEntity;
    }
}
