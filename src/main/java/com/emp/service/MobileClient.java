package com.emp.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emp.entity.Mobiles;

@FeignClient(url="http://localhost:9004",value="Mobile-Client")
public interface MobileClient {
	
@GetMapping("mobile/Employee/{employeeId}")
public List<Mobiles> getMobies(@PathVariable Integer employeeId);

}
