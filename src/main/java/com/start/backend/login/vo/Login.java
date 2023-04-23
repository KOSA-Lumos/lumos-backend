package com.start.backend.login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	private String userId;
	private String userPw;
	private String userName;
	private String nickName;
	private String signupDate;
	private String updateDate;
	private String deleteDate;
}