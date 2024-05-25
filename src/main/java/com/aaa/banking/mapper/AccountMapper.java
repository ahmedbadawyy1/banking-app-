package com.aaa.banking.mapper;

import com.aaa.banking.dto.AccountDto;
import com.aaa.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccounHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccounHolderName(),
                account.getBalance()
        );
        return accountDto;
    }

}
