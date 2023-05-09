package com.start.backend.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private int zonecode;
	private String roadAddress;
	private String detailAddress;
	private String nickname;
	private String phone;
	private String status;
	private String adminYn;
	
	
	
}