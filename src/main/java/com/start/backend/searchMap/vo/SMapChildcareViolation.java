package com.start.backend.searchMap.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMapChildcareViolation {

	private int centerNum;
	private int childcareViolationNum;
	private String childcareViolationName;
	private String childcareViolationState;
	private String childcareViolationCity;
	private String childcareViolationKindtype;
	private String childcareViolationNowmaster;
	private String childcareViolationNowledger;
	private String childcareViolationMaster;
	private String childcareViolationLedger;
	private String childcareViolationAddress;
	private String childcareViolationAct;
	private String childcareViolationContent;
	
}
