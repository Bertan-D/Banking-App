package com.example.bankingapp.service;

import com.example.bankingapp.exception.InsufficientFundsException;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.Transfer;
import com.example.bankingapp.repository.TransferRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransferService {
    private final TransferRepo transferRepo;
    private final AccountService accountService;

    public TransferService(TransferRepo transferRepo, AccountService accountService) {
        this.transferRepo = transferRepo;
        this.accountService = accountService;
    }

    @Transactional
    public Transfer create(Transfer request) {
        Account source = accountService.getById(request.getSource().getId());
        Account target = accountService.getById(request.getTarget().getId());

        if (source.getBalance().compareTo(request.getAmount()) > 0)
            throw new InsufficientFundsException("Source account doesn't have enough funds.");

        accountService.updateBalance(source.getId(), source.getBalance().subtract(request.getAmount()));
        accountService.updateBalance(target.getId(), target.getBalance().add(request.getAmount()));
        return transferRepo.save(request);
    }

    public ArrayList<Transfer> getAllForAccount(Account account) {
        return transferRepo.findAllBySource(account);
    }
}
