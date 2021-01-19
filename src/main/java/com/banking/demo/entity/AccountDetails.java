package com.banking.demo.entity;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="account_details")
public class AccountDetails {
    @Id
    private String accountNumber;

    @Column(name="name")
    private String name;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="balance",columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal balance;

    @Column (name = "lastUpdatedTimeStamp", columnDefinition = "TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP()")
    private String lastUpdatedTimeStamp;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = TransactionDetails.class)
    @JoinColumn(name ="accountNumber",referencedColumnName = "accountNumber")
    @Fetch(FetchMode.SUBSELECT)
    private List<TransactionDetails> transactions;
}
