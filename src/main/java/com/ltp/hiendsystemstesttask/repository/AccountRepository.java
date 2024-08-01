package com.ltp.hiendsystemstesttask.repository;

import com.ltp.hiendsystemstesttask.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
