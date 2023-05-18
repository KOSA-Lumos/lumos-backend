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
	private String centerState; // 시도
	private String centerCity; // 시군구
	private String centerType; // 유형
	private int centerExtendcare; // 연장 보육 여부

	// KidsdataDetail VO
	private String centerDetailState; // 시도
	private String centerDetailCity; // 시군구
	private String centerDetailBame; // 이름
	private String centerDetailClassification; // 분류. ex. 국공립, 직장
	private String centerDetailCenteropen; // 운영 상태. ex. 정상
	private int centerDetailOfficenumber; // 구분 번호? ex. 3159
	private String centerDetailAddress; // 상세주소
	private String centerDetailPhone; // 전화번호
	private String centerDetailFax; // Fax 번호
	private int centerDetailRoomcount; // 방 개수
	private int centerDetailRoomsize; // 방 크기;
	private String centerDetailPlaygroundcount; // 놀이터 수
	private int centerDetailTeachercount; // 선생님 수
	private int centerDetailRegularperson; // 정원
	private int centerDetailCurrentperson; // 현재 총원
	private double centerDetailLatitude; // 위도
	private double centerDetailLongitude; // 경도
	private String centerDetailVehicle; // 차량 운영 여부
	private String centerDetailHompage; // 홈페이지 주소
	private String centerDetailEstablish; // 설립일
	
}
