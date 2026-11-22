package com.example.bankingapp.dto;

import com.example.bankingapp.model.Account;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public record TransferDTO (
        UUID id,
        Account source,
        Account target,
        BigDecimal amount,
        String usage,
        ZonedDateTime timestamp
) {}
