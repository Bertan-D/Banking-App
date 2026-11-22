package com.example.bankingapp.repository;

import com.example.bankingapp.model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, UUID>{

}
