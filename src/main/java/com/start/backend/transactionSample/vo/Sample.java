package com.start.backend.transactionSample.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sample {

	private String sampleId;
	private String samplePw;
	private String sampleName;
	private String nickName;
	private String signupDate;
	private String updateDate;
	private String deleteDate;
}