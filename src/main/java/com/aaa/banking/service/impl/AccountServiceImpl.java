package com.aaa.banking.service.impl;

import com.aaa.banking.dto.AccountDto;
import com.aaa.banking.entity.Account;
import com.aaa.banking.mapper.AccountMapper;
import com.aaa.banking.repository.AccountRepository;
import com.aaa.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccount(Long id) {
     Account account = accountRepository
             .findById(id).
             orElseThrow(() -> new RuntimeException("Account does not exit"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id).
                orElseThrow(() -> new RuntimeException("Account does not exit"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id).
                orElseThrow(() -> new RuntimeException("Account does not exit"));
        if (account.getBalance()< amount) {
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map((account) -> AccountMapper.mapToAccountDto(account))
                        .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }


}
