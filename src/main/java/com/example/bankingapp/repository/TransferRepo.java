package com.example.bankingapp.repository;

import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface TransferRepo extends JpaRepository<Transfer, UUID> {

    public ArrayList<Transfer> findAllBySource(Account source);
}
