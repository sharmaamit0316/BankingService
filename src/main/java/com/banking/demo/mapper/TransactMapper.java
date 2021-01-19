package com.banking.demo.mapper;

import com.banking.demo.entity.AccountDetails;
import com.banking.demo.entity.TransactionDetails;
import com.banking.demo.exception.BankingException;
import com.banking.demo.model.Account;
import com.banking.demo.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactMapper {

    List<Transaction> transactionDetailsToTransaction(List<TransactionDetails> accountDetails) throws BankingException;

    List<TransactionDetails> transactionToTransactionDetails(List<Transaction> account) throws BankingException;

    Transaction singleTransactionDetailsToTransaction(TransactionDetails accountDetails) throws BankingException;

    TransactionDetails singleTransactionToTransactionDetails(Transaction account) throws BankingException;
}
