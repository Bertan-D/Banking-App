package com.example.bankingapp.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record AccountDTO (
        UUID id,
        String iban,
        String holderName,
        BigDecimal balance,
        Date openDate
) {}
