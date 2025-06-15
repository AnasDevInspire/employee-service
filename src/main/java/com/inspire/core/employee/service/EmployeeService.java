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
	private EmployeeRepository repository;

	public Employee createEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return repository.findById(id).filter(emp -> emp.getIsDeleted() == 0);
	}

	public List<Employee> getAllEmployees() {
		return repository.findAllByIsDeleted(0);
	}

	public Employee updateEmployee(Long id, Employee updated) {
		return repository.findById(id).map(existing -> {
			existing.setName(updated.getName());
			existing.setEmail(updated.getEmail());
			existing.setAccountId(updated.getAccountId());
			return repository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Employee not found"));
	}

	public void softDeleteEmployee(Long id) {
		repository.findById(id).ifPresent(emp -> {
			emp.setIsDeleted(1);
			repository.save(emp);
		});
	}
}
