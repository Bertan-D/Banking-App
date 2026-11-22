package com.example.bankingapp.controller;

import com.example.bankingapp.model.Transfer;
import com.example.bankingapp.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping()
    public ResponseEntity<Transfer> create(Transfer request) {
        if(request.getSource().equals(request.getTarget()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Transfer created = transferService.create(request);

        return ResponseEntity.created(URI.create("/transfers" + created.getId())).body(created);
    }
}
