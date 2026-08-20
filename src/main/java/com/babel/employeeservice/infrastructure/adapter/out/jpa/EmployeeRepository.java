package com.babel.employeeservice.infrastructure.adapter.out.jpa;

import com.babel.employeeservice.infrastructure.adapter.out.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
