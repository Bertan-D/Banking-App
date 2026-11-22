package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountDTO;
import com.example.bankingapp.exception.AccountNotFoundException;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.repository.AccountRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account createAccount(AccountDTO request) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setIban(request.iban());
        account.setBalance((request.balance() != null) ? request.balance() : BigDecimal.valueOf(0));
        account.setHolderName(request.holderName());
        account.setOpenDate(Date.from(Instant.now()));

        return accountRepo.save(account);
    }

    public Account getById(UUID id) {
        return accountRepo.findById(id).orElseThrow(
                () -> new AccountNotFoundException(id.toString())
        );
    }

    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    public Account updateBalance(UUID id, BigDecimal newBalance) {
        Account account = getById(id);
        account.setBalance(newBalance);
        return accountRepo.save(account);
    }
}
