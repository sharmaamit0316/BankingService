package com.banking.demo.model;

import com.banking.demo.util.DateHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class SearchCriteria {

    @NotNull(message = "AccountNumber cannot be null")
    private String accountNumber;

    @NotNull(message = "StartDate cannot be null")
    @JsonDeserialize(using = DateHandler.class)
    private Date startDate;

    @NotNull(message = "StartDate cannot be null")
    @JsonDeserialize(using = DateHandler.class)
    private Date endDate;

    private String type;
}
