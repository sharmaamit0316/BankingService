package com.banking.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessages extends RuntimeException {
    private HttpStatus status;
    private String message;
    private List<?> errors;


}
