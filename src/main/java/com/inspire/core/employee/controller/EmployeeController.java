package com.inspire.core.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.core.employee.entity.Employee;
import com.inspire.core.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping
	////////////////////////////////////////////
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		return ResponseEntity.ok(service.createEmployee(employee));
	}

	@GetMapping
	////////////////////////////////////////////
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	@GetMapping("/{id}")
	////////////////////////////////////////////
	public ResponseEntity<Employee> getById(@PathVariable Long id) {
		Optional<Employee> optinalEmployee = service.getEmployeeById(id);
		if (optinalEmployee.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optinalEmployee.get());
	}

	@PutMapping("/{id}")
	////////////////////////////////////////////
	public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
		return ResponseEntity.ok(service.updateEmployee(id, employee));
	}

	@DeleteMapping("/{id}")
	////////////////////////////////////////////
	public ResponseEntity<Void> softDelete(@PathVariable Long id) {
		service.deleteEmployee(id);
		return ResponseEntity.ok().build();
	}
}
