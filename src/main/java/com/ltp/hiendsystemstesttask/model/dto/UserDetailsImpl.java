package com.ltp.hiendsystemstesttask.model.dto;

import com.ltp.hiendsystemstesttask.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserDetailsImpl(final UserEntity userEntity) {
        if(Objects.isNull(userEntity)) {
            username = new String();
            password = new String();
            authorities = List.of();
            return;
        }

        username = userEntity.getUsername();
        password = userEntity.getPassHash();
        authorities = List.of(new SimpleGrantedAuthority(userEntity.getUserRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
