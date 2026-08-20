package com.babel.employeeservice.infrastructure.adapter.in.dto;

import jakarta.validation.constraints.*;

public record EmployeeRequest(

        @NotBlank(message = "firstname cannot be blank")
        @Size(min = 3, max = 50)
        String firstName,

        String middleName,

        @NotBlank(message = "firstname cannot be blank")
        @Size(min = 3, max = 50)
        String lastName,

        String secondLastName,

        @NotNull(message = "age cannot be null")
        @Min(value = 18, message = "age must be at least 18")
        @Max(value = 70, message = "age must be less than 70")
        Integer age,

        String gender,

        @NotNull(message = "birthdate cannot be blank")
        String birthDate,

        String position) {
}
