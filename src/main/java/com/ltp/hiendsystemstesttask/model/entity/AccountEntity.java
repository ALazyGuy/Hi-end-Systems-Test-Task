package com.ltp.hiendsystemstesttask.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int money;
    private boolean isActive;
    @OneToOne(mappedBy = "account")
    private UserEntity userEntity = null;

    @Override
    public String toString() {
        return String.format("{id: %d, money: %d, isActive: %b}", id, money, isActive);
    }

    @Override
    public int hashCode() {
        int hashCode = money + id.intValue() * 37 + (isActive ? 3 : 7);
        return hashCode * 17;
    }

    @Override
    public boolean equals(final Object o) {
        if(!(o instanceof AccountEntity)) {
            return false;
        }

        final AccountEntity accountEntity = (AccountEntity) o;
        return id == accountEntity.getId()
                && money == accountEntity.getMoney()
                && !(isActive ^ accountEntity.isActive())
                && hashCode() == o.hashCode();
    }
}
