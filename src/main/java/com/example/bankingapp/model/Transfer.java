package com.example.bankingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false)
    private Account source;
    @ManyToOne(optional = false)
    private Account target;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String usage;
    @Column(nullable = false)
    private ZonedDateTime timestamp;
}
