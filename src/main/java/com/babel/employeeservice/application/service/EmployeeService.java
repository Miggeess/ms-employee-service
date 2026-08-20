package com.babel.employeeservice.application.service;

import com.babel.employeeservice.application.port.EmployeeUseCase;
import com.babel.employeeservice.application.validator.DateValidator;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeDataResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeRequest;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeGeneralResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeResponse;
import com.babel.employeeservice.infrastructure.adapter.out.entity.EmployeeEntity;
import com.babel.employeeservice.infrastructure.adapter.out.jpa.EmployeeRepository;
import com.babel.employeeservice.infrastructure.adapter.out.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.babel.employeeservice.application.common.EmployeeConstants.*;

@Service
public class EmployeeService implements EmployeeUseCase {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository){
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDataResponse> getAllEmployee() {
        LOG.info("Entered GET - /api/v1/employee in EmployeeService - getAllEmployee init");

        return employeeRepository.findAll().stream().map(employeeMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public EmployeeDataResponse addEmployee(EmployeeRequest employeeRequest) {
        LOG.info("Entered POST - /api/v1/employee in EmployeeService - addEmployee init");
        DateValidator.validateAndParse(employeeRequest.birthDate());
        return employeeMapper.toModel(employeeRepository.save(employeeMapper.toEntity(employeeRequest)));
    }

    @Override
    public EmployeeResponse<EmployeeGeneralResponse> deleteEmployee(Long id) {
        LOG.info("Entered DELETE - /api/v1/employee/{id} in EmployeeService - deleteEmployee init");

        if(!employeeRepository.existsById(id)){
            return new EmployeeResponse<>(HttpStatus.NOT_FOUND, new EmployeeGeneralResponse(STATUS_FAIL, MESSAGE_FAIL));
        }
        employeeRepository.deleteById(id);
        return new EmployeeResponse<>(HttpStatus.OK, new EmployeeGeneralResponse(STATUS_OK, MESSAGE_OK));
    }

    @Override
    public EmployeeResponse<EmployeeDataResponse> putEmployee(Long id, EmployeeRequest employeeRequest) {
        LOG.info("Entered PUT - /api/v1/employee/{id} in EmployeeController - putEmployee init");

        if(!employeeRepository.existsById(id)){
            return new EmployeeResponse<>(HttpStatus.NOT_FOUND, null);
        }
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeRequest);
        employeeEntity.setId(id);
        return new EmployeeResponse<>(HttpStatus.OK, employeeMapper.toModel(employeeRepository.saveAndFlush(employeeEntity)));
    }
}