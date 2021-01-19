package com.banking.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transaction_details")
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    private String accountNumber;

    @Column (name = "transactionTimeStamp", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()")
    private String transactionTimeStamp;//yyyy-MM-dd'T'HH:mm:ss.SSS'Z'

    @Column(name="amount",columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal amount;

    @Column(name="type",nullable = false)
    private String type;

}
