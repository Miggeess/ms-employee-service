package com.babel.employeeservice.application.validator;

import com.babel.employeeservice.domain.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.babel.employeeservice.application.common.EmployeeConstants.FORMATTER_DD_MM_YYYY;

public final class DateValidator {

    private DateValidator(){}

    public static void validateAndParse(String date){
        try {
            LocalDate.parse(date, FORMATTER_DD_MM_YYYY);
        } catch (DateTimeParseException ex){
            throw new InvalidDateFormatException("birthdate mus follow format dd-mm-yyyy");
        }
    }
}
