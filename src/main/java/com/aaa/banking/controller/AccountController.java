package com.aaa.banking.controller;

import com.aaa.banking.dto.AccountDto;
import com.aaa.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
    return new ResponseEntity<>( accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long id) {
        return new ResponseEntity<>( accountService.getAccount(id), HttpStatus.OK);

    }

    @PutMapping("/deposit/{id}")
    public  ResponseEntity<AccountDto> deposit(@PathVariable long id,
                                               @RequestBody Map<String, Double> requset) {
        Double amount = requset.get("amount");
        return new ResponseEntity<>( accountService.deposit(id, amount), HttpStatus.OK);
    }

    @PutMapping("/withdraw/{id}")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable long id,
                                               @RequestBody Map<String, Double> requset) {
        Double amount = requset.get("amount");
        return new ResponseEntity<>( accountService.withdraw(id, amount), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("deleted");
    }
}
