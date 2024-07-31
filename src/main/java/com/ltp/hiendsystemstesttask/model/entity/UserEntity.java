package com.ltp.hiendsystemstesttask.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String passHash;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public String toString() {
        final String formatString = "UserEntity -> {id: %d, username: '%s', passHash: '%s', role: '%s'}";
        return String.format(formatString, id, username, passHash, userRole.name());
    }

    @Override
    public int hashCode() {
        int hashCode = username.hashCode() + passHash.hashCode()
                + (userRole == UserRole.ROLE_USER ? 0 : 1);
        return hashCode * 17 + id.intValue();
    }

    @Override
    public boolean equals(final Object o) {
        if(!(o instanceof UserEntity)) {
            return false;
        }

        final UserEntity userEntity = (UserEntity) o;
        return id == userEntity.getId()
                && username.equals(userEntity.getUsername())
                && passHash.equals(userEntity.getPassHash())
                && userRole == userEntity.getUserRole();
    }
}

