package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoApiAddressRoadAddress {

	private String address_name;
	private String building_name;
	private int main_building_no;
	private String region_1depth_name;
	private String region_2depth_name;
	private String region_3depth_name;
	private String road_name;
	private String sub_building_no;
	private String underground_yn;
	private double x;
	private double y;
	private int zone_no;
	
}
