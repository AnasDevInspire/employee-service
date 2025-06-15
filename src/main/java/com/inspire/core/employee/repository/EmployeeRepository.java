package com.inspire.core.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inspire.core.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByIsDeleted(int isDeleted);
}
