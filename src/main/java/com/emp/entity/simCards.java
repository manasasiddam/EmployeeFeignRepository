package com.emp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class simCards {
	
	private Integer simId;
	private String netWorkName;

	private Long emiNumber;

	private Integer mobileId;
}
