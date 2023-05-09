package com.start.backend.searchMap.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMapCenterKidsdataDetail {

	// 공통
	private int centerNum;
	
	// Center VO
	private String centerName;
	private String centerCategory;
	private String centerState;
	private String centerCity;
	private String centerType;
	private int centerExtendcare;

	// KidsdataDetail VO
	private String centerDetailState;
	private String centerDetailCity;
	private String centerDetailBame;
	private String centerDetailClassification;
	private String centerDetailCenteropen;
	private int centerDetailOfficenumber;
	private String centerDetailAddress;
	private String centerDetailPhone;
	private String centerDetailFax;
	private int centerDetailRoomcount;
	private int centerDetailRoomsize;
	private String centerDetailPlaygroundcount;
	private int centerDetailTeachercount;
	private int centerDetailRegularperson;
	private int centerDetailCurrentperson;
	private double centerDetailLatitude;
	private double centerDetailLongitude;
	private String centerDetailVehicle;
	private String centerDetailHompage;
	private String centerDetailEstablish;
	
}
