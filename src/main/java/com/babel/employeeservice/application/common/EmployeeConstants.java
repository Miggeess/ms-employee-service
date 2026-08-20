package com.babel.employeeservice.application.common;

import java.time.format.DateTimeFormatter;

public final class EmployeeConstants {

    public static final int STATUS_OK = 0;
    public static final String MESSAGE_OK = "Success";
    public static final int STATUS_FAIL = 1;
    public static final String MESSAGE_FAIL = "Error";

    public static final DateTimeFormatter FORMATTER_DD_MM_YYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}
