package com.ltp.hiendsystemstesttask.model.dto;

import lombok.Data;

@Data
public class ChangeAccountStatusRequest {
    private String username;
    private boolean status;
}
