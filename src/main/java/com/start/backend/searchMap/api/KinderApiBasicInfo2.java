package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiBasicInfo2 {
	
	private String officeedu;
	private String subofficeedu;
	private String kinderCode; // 유치원코드
	private String kindername; // 유치원명
	private String establish; // 설립유형
	private String rppnname; // 대표자명
	private String ldgrname; // 원장명
	private String edate; // 설립일
	private String odate; // 개원일
	private String addr; // 상세주소
	private String telno; // 전화번호
	private String hpaddr; // 홈페이지 주소
	private String opertime; // 운영시간
	private int clcnt3; // int?
	private int clcnt4; // int?
	private int clcnt5; // int?
	private int mixclcnt; // int?
	private int shclcnt; // int?
	private int prmstfcnt; // 인가 총 정원 수
	private int ag3fpcnt; // int?
	private int ag4fpcnt; // int?
	private int ag5fpcnt; // int?
	private int mixfpcnt; // int?
	private int spcnfpcnt; // int?
	private int ppcnt3; // int?
	private int ppcnt4; // int?
	private int ppcnt5; // int?
	private int mixppcnt; // int?
	private int shppcnt; // int?
	private int pbnttmng; // int?
	
}
