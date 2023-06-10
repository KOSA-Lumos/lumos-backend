package com.start.backend.searchMap.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamCenterAddress {

	private String centerName;
	private String centerAddress;
	private String centerState;
	private String centerCity;
	private Double centerLatitude;
	private Double centerLongitude;
	private boolean kidscareOption;
	private boolean kinderOption;
	private boolean extendcareOption;

}
