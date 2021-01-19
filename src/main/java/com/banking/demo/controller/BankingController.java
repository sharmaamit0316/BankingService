package com.banking.demo.controller;


import com.banking.demo.model.Account;
import com.banking.demo.model.SearchCriteria;
import com.banking.demo.model.Transaction;
import com.banking.demo.service.BankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/banking")
@Validated
public class BankingController {

    private static final Logger logger= LoggerFactory.getLogger(BankingController.class);

    @Autowired
    private BankingService bankingService;

    /**
     * API to update the Account Details
     * @param account
     * @return
     */
    @PostMapping (value = "/updateAccount")
    public Account updateAccountDetails(@Valid @RequestBody final Account account) {
        return bankingService.updateAccountDetails(account);
    }

    /**
     * API to get transactions based on AccountNumber, Type and Date range
     * @param searchCriteria
     * @return
     */
    @PostMapping(value="/getTransactions")
    public List<Transaction> getTransactions(@Valid @RequestBody final SearchCriteria searchCriteria){
            return bankingService.searchTransaction(searchCriteria);
    }

    /**
     * API to get the Account details based on AccountNumber
     * @param searchCriteria
     * @return
     */
    @PostMapping(value="/getAccount")
    public Account searchAccount(@Valid @RequestBody final SearchCriteria searchCriteria){
        return bankingService.searchAccount(searchCriteria);
    }

    /**
     * API to save the transactions for an account
     * @param transaction
     * @return
     */
    @PostMapping (value = "/insertTransaction")
    public Account insertTransaction(@Valid @RequestBody final Transaction transaction) {
        return bankingService.insertTransaction(transaction);
    }

}
