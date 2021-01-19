package com.banking.demo.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Transaction {

    private Integer transactionId;

    @NotNull(message = "Account Number is required")
    @Size(min = 12,max=12,message = "Account Number should be 12 digits")
    private String accountNumber;

    @NotNull(message = "Please pass the Transaction timestamp")
    private String transactionTimeStamp;

    @NotNull(message = "Amount is required")
    @Min(value = 0,message = "Amount cannot be less than 0")
    private BigDecimal amount;

    @NotNull(message = "Please mention the Transaction type")
    private String type;




}
