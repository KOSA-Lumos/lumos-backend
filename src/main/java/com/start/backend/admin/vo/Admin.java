package com.start.backend.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	private String userId;
	private String userPw;
	private String userName;
	private String nickName;
	private String signupDate;
	private String updateDate;
	private String deleteDate;
}