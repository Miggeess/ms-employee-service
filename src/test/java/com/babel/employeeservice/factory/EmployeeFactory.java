package com.babel.employeeservice.factory;

import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeDataResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeGeneralResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeRequest;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeResponse;
import com.babel.employeeservice.infrastructure.adapter.out.entity.EmployeeEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public final class EmployeeFactory {

    private EmployeeFactory(){}

    public static List<EmployeeDataResponse> getAllEmployee(){
        return List.of(getEmployee());
    }

    public static EmployeeDataResponse getEmployee(){
        return new EmployeeDataResponse(1L,"Miguel", "Angel", "Perez", "Mejia", 35, "Masculino", "04-05-1991", "Java Developer Sr");
    }

    public static EmployeeResponse<EmployeeGeneralResponse> getEmployeeResponse(){
        return new EmployeeResponse<>(HttpStatus.OK, new EmployeeGeneralResponse(0,"Success"));
    }

    public static EmployeeResponse<EmployeeDataResponse> getEmployeeDataResponseResponse(){
        return new EmployeeResponse<>(HttpStatus.OK, getEmployee());
    }

    public static List<EmployeeEntity> getAllEmployeeEntity(){
        return List.of(getEmployeeEntity());
    }

    public static EmployeeEntity getEmployeeEntity(){
        return new EmployeeEntity(1L, "Miguel", "Angel", "Perez", "Mejia", 35, "Masculino", "04-05-1991", "Java Developer Sr");
    }

    public static List<EmployeeRequest> employeeRequestList(){
        return List.of(getEmployeeRequest());
    }

    public static EmployeeRequest getEmployeeRequest(){
        return new EmployeeRequest( "Miguel", "Angel", "Perez", "Mejia", 35, "Masculino", "04-05-1991", "Java Developer Sr");
    }
}
