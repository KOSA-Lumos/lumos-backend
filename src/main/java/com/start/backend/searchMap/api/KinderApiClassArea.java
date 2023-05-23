package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiClassArea {

	private String officeedu;
	private String subofficeedu;
	private String kinderCode;
	private String kindername;
	private String establish;
	private String crcnt; // 단위 : 개
	private String clsrarea; // 단위 : m^2
	private String phgrindrarea; // 단위 : m^2
	private String hlsparea; // 단위 : m^2
	private String ktchmssparea; // 단위 : m^2
	private String otsparea; // 단위 : m^2
	private int pbntTmng;
	
}
