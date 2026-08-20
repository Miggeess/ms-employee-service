package com.babel.employeeservice.application;

import com.babel.employeeservice.application.service.EmployeeService;
import com.babel.employeeservice.factory.EmployeeFactory;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeDataResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeGeneralResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeRequest;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeResponse;
import com.babel.employeeservice.infrastructure.adapter.out.entity.EmployeeEntity;
import com.babel.employeeservice.infrastructure.adapter.out.jpa.EmployeeRepository;
import com.babel.employeeservice.infrastructure.adapter.out.mapper.EmployeeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    private static List<EmployeeRequest> employeeRequest;

    @BeforeEach
    public void init(){
        employeeRequest = EmployeeFactory.employeeRequestList();
    }

    @Test
    public void getAllEmployeeTest(){
        Mockito.when(employeeRepository.findAll()).thenReturn(EmployeeFactory.getAllEmployeeEntity());
        Mockito.when(employeeMapper.toModel(Mockito.any(EmployeeEntity.class))).thenReturn(EmployeeFactory.getEmployee());
        Assertions.assertNotNull(employeeService.getAllEmployee());
    }

    @Test
    public void addAllEmployeeTest(){
        Mockito.when(employeeMapper.toEntity(Mockito.any(EmployeeRequest.class))).thenReturn(EmployeeFactory.getEmployeeEntity());
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(EmployeeFactory.getEmployeeEntity());
        Mockito.when(employeeMapper.toModel(Mockito.any(EmployeeEntity.class))).thenReturn(EmployeeFactory.getEmployee());
        List<EmployeeDataResponse> response = employeeService.addAllEmployee(employeeRequest);
        Assertions.assertNotNull(response);
    }

    @Test
    public void deleteEmployeeTest(){
        Mockito.when(employeeRepository.existsById(Mockito.any(Long.class))).thenReturn(true);
        EmployeeResponse<EmployeeGeneralResponse> response = employeeService.deleteEmployee(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    public void putEmployeeTest(){
        Mockito.when(employeeRepository.existsById(Mockito.any(Long.class))).thenReturn(true);
        Mockito.when(employeeRepository.saveAndFlush(Mockito.any(EmployeeEntity.class))).thenReturn(EmployeeFactory.getEmployeeEntity());
        Mockito.when(employeeMapper.toEntity(Mockito.any(EmployeeRequest.class))).thenReturn(EmployeeFactory.getEmployeeEntity());
        Mockito.when(employeeMapper.toModel(Mockito.any(EmployeeEntity.class))).thenReturn(EmployeeFactory.getEmployee());
        EmployeeResponse<EmployeeDataResponse> response = employeeService.putEmployee(1L, EmployeeFactory.getEmployeeRequest());
        Assertions.assertNotNull(response);
    }

}
