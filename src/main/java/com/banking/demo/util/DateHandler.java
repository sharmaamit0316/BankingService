package com.banking.demo.util;

import com.banking.demo.exception.BankingException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler extends StdDeserializer<Date> {

    protected DateHandler() {
        this(null);
    }
    protected DateHandler(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
        } catch (Exception e) {
            throw new BankingException(HttpStatus.BAD_REQUEST, "Please pass valid Date");
        }

    }
}
