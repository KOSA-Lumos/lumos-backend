package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoApiAddressAddress {

	private String address_name;
	private long b_code;
	private long h_code;
	private int main_address_no;
	private String mountain_yn;
	private String region_1depth_name;
	private String region_2depth_name;
	private String region_3depth_h_name;
	private String region_3depth_name;
	private String sub_address_no;
	private double x;
	private double y;
	
}
