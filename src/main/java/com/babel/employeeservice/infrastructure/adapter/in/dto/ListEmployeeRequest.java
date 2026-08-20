package com.babel.employeeservice.infrastructure.adapter.in.dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class ListEmployeeRequest {

    @Valid
    private List<EmployeeRequest> employees;
}
