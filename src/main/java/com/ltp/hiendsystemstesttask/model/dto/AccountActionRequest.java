package com.ltp.hiendsystemstesttask.model.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AccountActionRequest {
    @Min(value = 0, message = "Money amount cannot be less than 0")
    private int amount;
}
