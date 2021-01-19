package com.banking.demo.model;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @NotNull(message = "Account Number is required")

    //@Min(value = 2,message = "Account Number should be 12 digits")
    //@Max(value = 2, message = "Account Number should be 12 digits")
    @Size(min = 12,max=12,message = "Account Number should be 12 digits")
    private String accountNumber;

    private String name;

    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$")
    private String phoneNumber;

    @NotNull(message = "Balance is required")
    @Min(value = 0,message = "Balance cannot be less than 0")
    private BigDecimal balance;


    private String lastUpdatedTimeStamp;//yyyy-MM-dd'T'HH:mm:ss.SSS'Z'


    private List<Transaction> transactions;
}
