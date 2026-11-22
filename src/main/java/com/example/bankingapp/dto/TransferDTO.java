package com.example.bankingapp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferDTO (
        UUID id,
        UUID sourceId,
        UUID targetId,
        BigDecimal amount,
        String usage
) {}
