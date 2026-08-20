package com.babel.employeeservice.application.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.babel.employeeservice.application.common.EmployeeConstants.FORMATTER_DD_MM_YYYY;

public final class DateValidator {

    private DateValidator(){}

    public static boolean validateAndParse(String date){
        try {
            LocalDate.parse(date, FORMATTER_DD_MM_YYYY);
            return true;
        } catch (DateTimeParseException ex){
            return false;
        }
    }
}
