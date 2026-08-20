package com.babel.employeeservice.infrastructure.adapter.in.dto;

import java.time.LocalDate;

public record EmployeeRequest(
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        Integer age,
        String gender,
        LocalDate birthDate,
        String position) {
}
