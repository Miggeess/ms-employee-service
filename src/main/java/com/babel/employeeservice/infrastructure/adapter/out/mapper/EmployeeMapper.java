package com.babel.employeeservice.infrastructure.adapter.out.mapper;

import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeDataResponse;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeRequest;
import com.babel.employeeservice.infrastructure.adapter.out.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeRequest employeeRequest){
        return new EmployeeEntity(null, employeeRequest.firstName(), employeeRequest.middleName(),
                employeeRequest.lastName(), employeeRequest.secondLastName(), employeeRequest.age(),
                employeeRequest.gender(), employeeRequest.birthDate(), employeeRequest.position());
    }

    public EmployeeDataResponse toModel(EmployeeEntity employeeEntity){
        return new EmployeeDataResponse(employeeEntity.getId(), employeeEntity.getFirstName(), employeeEntity.getMiddleName(), employeeEntity.getLastName(),
                employeeEntity.getSecondLastName(), employeeEntity.getAge(), employeeEntity.getGender(),
                employeeEntity.getBirthDate(), employeeEntity.getPosition());
    }
}
