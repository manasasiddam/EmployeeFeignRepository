package com.emp.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mobiles {
	private Integer mobileId;

	private String mobileName;

	private Double mobilePrice;

	private Integer employeeId;
	
	transient List<simCards> sims;
}
