package com.example.bankingapp.controller;

import com.example.bankingapp.dto.AccountDTO;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.Transfer;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransferService transferService;

    public AccountController(AccountService accountService, TransferService transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Account> create(@Valid AccountDTO accountDto) {
        if(accountDto.balance().compareTo(BigDecimal.ZERO) < 0)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Account created = accountService.createAccount(accountDto);
        return ResponseEntity.created(URI.create("/accounts/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}/transfers")
    public ArrayList<Transfer> getTransfersByAccountId(@PathVariable UUID id) {
        Account account = accountService.getById(id);
        return transferService.getAllForAccount(account);
    }

}
