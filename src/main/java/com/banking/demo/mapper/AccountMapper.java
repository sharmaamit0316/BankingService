package com.banking.demo.mapper;

import com.banking.demo.entity.AccountDetails;
import com.banking.demo.exception.BankingException;
import com.banking.demo.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account accountDetailsToAccount(AccountDetails accountDetails) throws BankingException;

    AccountDetails accountToAccountDetails(Account account) throws BankingException;
}
