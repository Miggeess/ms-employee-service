package com.babel.employeeservice.infrastructure.adapter.in.dto;

import org.springframework.http.HttpStatus;

public record EmployeeResponse<T>(HttpStatus httpStatus, T body) {
}
