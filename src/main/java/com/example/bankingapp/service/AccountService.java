package com.example.bankingapp.service;

import com.example.bankingapp.model.Account;
import com.example.bankingapp.repository.AccountRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account createAccount(Account request) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setIban(request.getIban());
        account.setBalance((request.getBalance() != null) ? request.getBalance() : BigDecimal.valueOf(0));
        account.setHolderName(request.getHolderName());
        account.setOpenDate(Date.from(Instant.now()));

        return accountRepo.save(account);
    }

    public Account getById(UUID id) {
        return accountRepo.findById(id).orElse(null);
    }

    public ArrayList<Account> getAll() {
        return accountRepo.getAll();
    }

    public Account updateBalance(UUID id, BigDecimal newBalance) {
        Account account = getById(id);
        account.setBalance(newBalance);
        return accountRepo.save(account);
    }
}
