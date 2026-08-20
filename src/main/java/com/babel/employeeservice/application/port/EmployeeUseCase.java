package com.babel.employeeservice.application.port;

import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeDataResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeRequest;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeGeneralResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeUseCase {

    List<EmployeeDataResponse> getAllEmployee();

    List<EmployeeDataResponse> addAllEmployee(List<EmployeeRequest> employeeRequest);

    EmployeeResponse<EmployeeGeneralResponse> deleteEmployee(Long id);

    EmployeeResponse<EmployeeDataResponse> putEmployee(Long id, EmployeeRequest employeeRequest);
}
