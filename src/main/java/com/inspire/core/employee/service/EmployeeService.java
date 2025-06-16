package com.inspire.core.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspire.core.employee.entity.Employee;
import com.inspire.core.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	////////////////////////////////////////////
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	////////////////////////////////////////////
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	////////////////////////////////////////////
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	////////////////////////////////////////////
	public Employee updateEmployee(Long id, Employee updated) {
		Optional<Employee> optinalEmployee = employeeRepository.findById(id);

		if (optinalEmployee.isEmpty()) {
			throw new RuntimeException("Employee not found");
		}

		Employee employee = optinalEmployee.get();

		employee.setAccountId(updated.getAccountId());
		employee.setName(updated.getName());
		employee.setEmail(updated.getEmail());
		employee.setAccountId(updated.getAccountId());

		return employeeRepository.save(employee);
	}

	////////////////////////////////////////////
	public void deleteEmployee(Long id) {
		employeeRepository.findById(id).ifPresent(emp -> {
			emp.setIsDeleted(1);
			employeeRepository.save(emp);
		});
	}
}
