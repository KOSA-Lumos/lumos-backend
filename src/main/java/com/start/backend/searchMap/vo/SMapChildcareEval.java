package com.start.backend.searchMap.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMapChildcareEval {

	private int centerNum;
	private String centerName;
	private String centerState;
	private String centerCity;
	private int childcareEvalRegionnum;
	private String childcareEvalGrade;
	private String childcareEvalCommunication;
	private String childcareEvalEnvironment;
	private String childcareEvalSafety;
	private String childcareEvalTeacher;
	private String childcareEvalDate;
	
}
