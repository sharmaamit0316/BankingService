package com.banking.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankingException extends RuntimeException {

    private HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
    private String message;

    public BankingException(String message){this.message=message;}
}
