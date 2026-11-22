package com.example.bankingapp.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.Collate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class Account {
    @NonNull
    private UUID id;
    @NonNull
    private String iban;
    @NonNull
    private String holderName;
    @NonNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;
    @NonNull
    private Date openDate;
}
