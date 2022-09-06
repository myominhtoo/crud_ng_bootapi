package com.lionel.bookmanagement.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class HttpResponse {
    
    private int httpStatusCode;
    private HttpStatus status;
    private String msg;

}
