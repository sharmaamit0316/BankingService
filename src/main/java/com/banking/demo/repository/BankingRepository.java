package com.banking.demo.repository;

import com.banking.demo.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankingRepository  extends JpaRepository<AccountDetails, String> {

    @Query("select distinct acc from AccountDetails acc join fetch acc.transactions transact  where transact.accountNumber=?1 and CAST(transact.transactionTimeStamp as date) between ?2 and ?3")
    AccountDetails findAccountAndTransactionDetails(String accountNumber, Date startDate, Date endDate);

}
