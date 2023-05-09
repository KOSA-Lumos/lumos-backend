package com.start.backend.searchMap.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMapCenter {

	private int centerNum;
	private String centerName;
	private String centerCategory;
	private String centerState;
	private String centerCity;
	private String centerType;
	private int centerExtendcare;
	
}
