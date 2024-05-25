package com.aaa.banking.service;

import com.aaa.banking.dto.AccountDto;
import com.aaa.banking.entity.Account;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
