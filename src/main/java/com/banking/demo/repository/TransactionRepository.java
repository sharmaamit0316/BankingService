package com.banking.demo.repository;

import com.banking.demo.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> {

    @Query("select transact from TransactionDetails transact  where transact.accountNumber=?1 and CAST(transact.transactionTimeStamp as date) between ?2 and ?3 order by transact.transactionTimeStamp desc")
    List<TransactionDetails> findByAccountNumberAndTransactionTimeStamp(String accountNumber, Date startDate, Date endDate);

    @Query("select transact from TransactionDetails transact  where transact.accountNumber=?1 and CAST(transact.transactionTimeStamp as date) between ?2 and ?3 and transact.type=?4 order by transact.transactionTimeStamp desc")
    List<TransactionDetails> findByAccountNumberAndTransactionTimeStampAndType(String accountNumber, Date startDate, Date endDate,String type);
}
