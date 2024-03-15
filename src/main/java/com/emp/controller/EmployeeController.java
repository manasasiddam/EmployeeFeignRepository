package com.emp.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.entity.Employee;
import com.emp.entity.Mobiles;
import com.emp.entity.simCards;
import com.emp.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	@Autowired
	private EmployeeService empservice;
	
	Logger loger=LoggerFactory.getLogger(EmployeeController.class);
	@PostMapping("/")
	
	public Employee addEmployee(@RequestBody Employee emp) {
		return empservice.addEmployee(emp);
		
	}
	
	
	public List<Employee> fallbackGetAllEmp(Exception e) {
		loger.info("Fallback called"+e.getMessage());
      Employee emp1= new Employee();
		
		Mobiles mb= new Mobiles();
		simCards sim1= new simCards();
		simCards sim2= new simCards();
		simCards sim3= new simCards();
		
		Mobiles mb1= new Mobiles();
		emp1.setEmployeeId(0);
		emp1.setEmpName("anonymous");
		mb.setEmployeeId(0);
		mb.setMobileName("googlePixel");
		mb.setMobileId(0);
		mb.setMobilePrice(0.0);
		
		
		mb1.setEmployeeId(0);
		mb1.setMobileName("googlePixel");
		mb1.setMobileId(0);
		mb1.setMobilePrice(0.0);
		
		sim1.setMobileId(mb.getMobileId());
		sim1.setNetWorkName("XXXX");
		sim1.setSimId(0);
		sim1.setEmiNumber(000000000L);
	
		
		sim2.setMobileId(mb.getMobileId());
		sim2.setNetWorkName("XXXX");
		sim2.setSimId(0);
		sim2.setEmiNumber(000000000L);
		

		sim3.setMobileId(mb.getMobileId());
		sim3.setNetWorkName("XXXX");
		sim3.setSimId(0);
		sim3.setEmiNumber(000000000L);
		
		mb.setSims(Arrays.asList(sim1,sim2));
		mb1.setSims(Arrays.asList(sim3));
		emp1.setMobiles(Arrays.asList(mb,mb1));

		
		
    	List<Employee> asList = Arrays.asList(emp1);      
    	return asList;
    }

 


	public Employee mobileFallback(Integer id,Exception e) {
	
		Employee emp1= new Employee();
		
		Mobiles mb= new Mobiles();
		simCards sim1= new simCards();
		simCards sim2= new simCards();
		simCards sim3= new simCards();
		
		Mobiles mb1= new Mobiles();
		emp1.setEmployeeId(id);
		emp1.setEmpName("anonymous");
		mb.setEmployeeId(id);
		mb.setMobileName("googlePixel");
		mb.setMobileId(0);
		mb1.setEmployeeId(id);
		mb1.setMobileName("googlePixel");
		mb1.setMobileId(0);
		mb1.setMobilePrice(0.0);
		
		
		sim1.setMobileId(mb.getMobileId());
		sim1.setNetWorkName("XXXX");
		sim1.setSimId(0);
		sim1.setEmiNumber(000000000L);
	
		
		sim2.setMobileId(mb.getMobileId());
		sim2.setNetWorkName("XXXX");
		sim2.setSimId(0);
		sim2.setEmiNumber(000000000L);
		

		sim3.setMobileId(mb.getMobileId());
		sim3.setNetWorkName("XXXX");
		sim3.setSimId(0);
		sim3.setEmiNumber(000000000L);
		
		mb.setSims(Arrays.asList(sim1,sim2));
		mb1.setSims(Arrays.asList(sim3));
		
		emp1.setMobiles(Arrays.asList(mb,mb1));
		
		return emp1;
		
	}
	@GetMapping("get/{id}")
	@CircuitBreaker(name="mobileBreaker",fallbackMethod = "mobileFallback")
	public Employee  getEmpbyId(@PathVariable Integer id) {
		return empservice.getEmployeebyId(id);
		
	}
	int retry =1;
	@GetMapping("get/all")
	@Retry(name="mobileRetry",fallbackMethod = "fallbackGetAllEmp")
	public List<Employee> getAllemp(){
		loger.info("Retry count : "+ retry);
		retry++;
		List<Employee> allEmployees = empservice.getAllEmployees();
		return allEmployees;
	}
	
	
	

}
