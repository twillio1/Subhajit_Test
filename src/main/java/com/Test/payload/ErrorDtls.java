package com.Test.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class ErrorDtls {
    private Date date;
    private String message;
    private String description;
}
