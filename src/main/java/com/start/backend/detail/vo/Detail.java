package com.start.backend.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detail {

	private int center_num;
	private String center_detail_state;
	private String center_detail_city;
	private String center_detail_name;
	private String center_detail_classification;
	private String center_detail_centeropen;
	private int center_detail_officenumber;
	private String center_detail_address;
	private String center_detail_phone;
	private String center_detail_fax;
	private int center_detail_roomcount;
	private int center_detail_roomsize;
	private String center_detail_playgroundcount;
	private int center_detail_teachercount;
	private int center_detail_regularperson;
	private int center_detail_currentperson;
	private String center_detail_Latitude;
	private String center_detail_longitude;
	private String center_detail_vehicle;
	private String center_detail_hompage;
	private String center_detail_establish;
}