package com.ltp.hiendsystemstesttask.repository;

import com.ltp.hiendsystemstesttask.model.entity.UserEntity;
import com.ltp.hiendsystemstesttask.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(final String username);
    boolean existsByUsername(final String username);
    List<UserEntity> findAllByUserRole(final UserRole userRole);
}
