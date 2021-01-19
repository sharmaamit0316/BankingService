package com.banking.demo.service;

import com.banking.demo.entity.AccountDetails;
import com.banking.demo.entity.TransactionDetails;
import com.banking.demo.exception.BankingException;
import com.banking.demo.mapper.AccountMapper;
import com.banking.demo.mapper.TransactMapper;
import com.banking.demo.model.Account;
import com.banking.demo.model.SearchCriteria;
import com.banking.demo.model.Transaction;
import com.banking.demo.repository.BankingRepository;
import com.banking.demo.repository.TransactionRepository;
import com.banking.demo.util.BankingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankingService {

    private BankingRepository bankingRepository;

    private TransactionRepository transactionRepository;


    private AccountMapper accountMapper;


    private TransactMapper transactMapper;

    public BankingService(BankingRepository bankingRepository,TransactionRepository transactionRepository,AccountMapper accountMapper,TransactMapper transactMapper){
        this.bankingRepository=bankingRepository;
        this.transactionRepository=transactionRepository;
        this.accountMapper=accountMapper;
        this.transactMapper=transactMapper;
    }

    /**
     * Updates the account details based on the account number
     * @param account
     * @return
     */
    public Account updateAccountDetails(Account account) {
        AccountDetails details= null;
        try {
            details = bankingRepository.findById(account.getAccountNumber()).orElseThrow(() -> new BankingException(HttpStatus.NOT_FOUND,"No Account found with "+ account.getAccountNumber()));
            details.setBalance(account.getBalance());
            bankingRepository.save(details);
            return accountMapper.accountDetailsToAccount(details);
        } catch (DataAccessException e) {
            throw new BankingException(BankingConstants.ACCOUNT_UPDATE_DATABASE_EXCEPTION);
        }catch (BankingException e) {
            throw e;
        }
        catch (Exception e) {
            throw new BankingException(BankingConstants.ACCOUNT_UPDATE_EXCEPTION);
        }

    }

    /**
     *  Finds the transactions based on the search criteria passed
     * @param searchCriteria
     * @return
     */
    public List<Transaction> searchTransaction(SearchCriteria searchCriteria) {
        try {
            if(!ObjectUtils.isEmpty(searchCriteria.getType())){
                List<TransactionDetails> transactionDetails=transactionRepository.findByAccountNumberAndTransactionTimeStampAndType(searchCriteria.getAccountNumber(),
                        searchCriteria.getStartDate(),searchCriteria.getEndDate(),searchCriteria.getType());
                return transactMapper.transactionDetailsToTransaction(transactionDetails);
            }
            List<TransactionDetails> transactionDetails=transactionRepository.findByAccountNumberAndTransactionTimeStamp(searchCriteria.getAccountNumber(),
                searchCriteria.getStartDate(),searchCriteria.getEndDate());
            return  transactMapper.transactionDetailsToTransaction(transactionDetails);
        } catch (DataAccessException e) {
            throw new BankingException(BankingConstants.TRANSACTION_DATABASE_EXCEPTION);
        }catch (Exception e) {
            throw new BankingException(BankingConstants.TRANSACTION_EXCEPTION);
        }
    }

    /**
     * Finds the account details based on the search criteria passed
     * @param searchCriteria
     * @return
     */
    public Account searchAccount(SearchCriteria searchCriteria) {
        try {
            AccountDetails accountDetails=bankingRepository.findAccountAndTransactionDetails(searchCriteria.getAccountNumber(),
                    searchCriteria.getStartDate(),searchCriteria.getEndDate());
            return accountMapper.accountDetailsToAccount(accountDetails);
        } catch (DataAccessException e) {
            throw new BankingException(BankingConstants.ACCOUNT_DATABASE_EXCEPTION);
        }catch (Exception e) {
            throw new BankingException(BankingConstants.ACCOUNT_EXCEPTION);
        }
    }

    /**
     * Inserts the transactions for an account
     * @param transaction
     * @return
     */
    @Transactional
    public Account insertTransaction(Transaction transaction) {
        String accountNumber=transaction.getAccountNumber();
        try {
            AccountDetails accountDetails= bankingRepository.findById(accountNumber).orElseThrow(() -> new BankingException(HttpStatus.NOT_FOUND,"No Account found with "+ accountNumber));
            BigDecimal balance= accountDetails.getBalance();
            if ("WITHDRAW".equalsIgnoreCase(transaction.getType()) && transaction.getAmount().compareTo(balance)!=1){
                accountDetails.setBalance(balance.subtract(transaction.getAmount()));
            }else if ("DEPOSIT".equalsIgnoreCase(transaction.getType())){
                accountDetails.setBalance(balance.add(transaction.getAmount()));
            }else{
                throw new BankingException(HttpStatus.BAD_REQUEST,BankingConstants.INVALID_TRANSACTION_TYPE);
            }
            TransactionDetails transactionDetails=transactMapper.singleTransactionToTransactionDetails(transaction);
            accountDetails.getTransactions().add(transactionDetails);
            bankingRepository.save(accountDetails);
            return accountMapper.accountDetailsToAccount(accountDetails);
        } catch (BankingException e) {
            throw e;
        }catch(DataAccessException e){
            throw new BankingException(BankingConstants.TRANSACTION_INSERT_DATABASE_EXCEPTION);
        }catch (Exception e){
            throw new BankingException(BankingConstants.TRANSACTION_INSERT_EXCEPTION);
        }

    }
}
