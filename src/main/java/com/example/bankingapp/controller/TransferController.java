package com.example.bankingapp.controller;

import com.example.bankingapp.model.Transfer;
import com.example.bankingapp.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping()
    public ResponseEntity<Transfer> create(Transfer request) {
        if(request.getSource().equals(request.getTarget()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Transfer created = transferService.create(request);

        return ResponseEntity.created(URI.create("/transfers/" + created.getId())).body(created);
    }
}
