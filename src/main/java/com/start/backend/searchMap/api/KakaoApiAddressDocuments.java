package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoApiAddressDocuments {

	private KakaoApiAddressAddress address; // 지번 주소 기반 기본 정보
	private String address_name; // 도로명 주소
	private String address_type; // 대상 타입
	private KakaoApiAddressRoadAddress road_address; // 도로명 주소 기반 기본 정보
	private double x;
	private double y;

}
