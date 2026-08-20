package com.babel.employeeservice.infrastructure.adapter.in.web;

import com.babel.employeeservice.application.port.EmployeeUseCase;
import com.babel.employeeservice.infrastructure.adapter.in.dto.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeUseCase employeeUseCase;

    public EmployeeController(EmployeeUseCase employeeUseCase){
        this.employeeUseCase = employeeUseCase;
    }

    @PostMapping
    public ResponseEntity<List<EmployeeDataResponse>> addAllEmployee(@RequestBody @Valid ListEmployeeRequest employeeRequest){
        LOG.info("Entered POST - /api/v1/employee in EmployeeController");

        return ResponseEntity.ok(employeeUseCase.addAllEmployee(employeeRequest.getEmployees()));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDataResponse>> getAllEmployees(){
        LOG.info("Entered GET - /api/v1/employee in EmployeeController");

        return ResponseEntity.ok(employeeUseCase.getAllEmployee());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeGeneralResponse> deleteEmployee(@PathVariable Long id){
        LOG.info("Entered DELETE - /api/v1/employee/{id} in EmployeeController");

        EmployeeResponse<EmployeeGeneralResponse> employeeResponse = employeeUseCase.deleteEmployee(id);
        return ResponseEntity.status(employeeResponse.httpStatus()).body(employeeResponse.body());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDataResponse> putEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest){
        LOG.info("Entered PUT - /api/v1/employee/{id} in EmployeeController");

        EmployeeResponse<EmployeeDataResponse> employeeResponse = employeeUseCase.putEmployee(id, employeeRequest);
        return ResponseEntity.status(employeeResponse.httpStatus()).body(employeeResponse.body());
    }
}
