package com.emp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.entity.Employee;
import com.emp.repository.EmployeRepository;
@Service
public class EmployeeServiceimp implements EmployeeService {
@Autowired
	private MobileClient mbclient;
@Autowired
	private EmployeRepository emprep;


	

	@Override
	public Employee addEmployee(Employee emp) {
		return emprep.save(emp);
	}

//	@Override
//	public List<Employee> getallEmployee() {
//		List<Employee> list = emprep.findAll();
////		List<Employee> list2 = list.stream().map(emp->{
////			emp.setMobiles(mbclient.getMobies(emp.getEmployeeId()));
////			return emp;
////		}).collect(Collectors.toList());	
//		return list;
//		
//	}

	@Override
	public Employee getEmployeebyId(Integer id) {

		Employee employee = emprep.findById(id).orElseThrow(() -> new IllegalArgumentException("emp id not found"));
		employee.setMobiles(mbclient.getMobies(id));
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = emprep.findAll();
		List<Employee> collect = list.stream().map(emp -> {
			emp.setMobiles(mbclient.getMobies(emp.getEmployeeId()));
			return emp;
		}).collect(Collectors.toList());
		return collect;
	}

}
