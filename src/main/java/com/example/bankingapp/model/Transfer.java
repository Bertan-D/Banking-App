package com.example.bankingapp.model;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class Transfer {
    @NonNull
    private UUID id;
    @NonNull
    private Account source;
    @NonNull
    private Account target;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String usage;
    @NonNull
    private ZonedDateTime timestamp;
}
