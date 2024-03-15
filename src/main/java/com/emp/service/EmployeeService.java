package com.emp.service;

import java.util.List;

import com.emp.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee emp);

	Employee getEmployeebyId(Integer id);
	
	List<Employee> getAllEmployees();
}
