package com.babel.employeeservice.domain.exception;

public class InvalidDateFormatException extends RuntimeException {

    public InvalidDateFormatException(String message){
        super(message);
    }
}
