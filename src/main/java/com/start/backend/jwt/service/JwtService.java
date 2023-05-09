package com.start.backend.jwt.service;

import java.util.Date;

import com.start.backend.user.vo.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtService {
	private String secretKey = "myKey";
	private long exp = 1000L * 60 * 60; // 토큰사용시간 1시간
	
	// 토큰 생성
	@SuppressWarnings("deprecation")
	public String createToken(User user) {
		return Jwts.builder()
				   .setHeaderParam("typ", "JWT") // 토큰 타입
				   .setSubject("userToken") // 토큰 제목
				   .setExpiration(new Date(System.currentTimeMillis() + exp)) // 토큰 유효시간
				   .claim("user", user) // 토큰에 담을 데이터
				   .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // secretKey를 사용하여 해싱 암호화 알고리즘 처리
				   .compact(); // 직렬화, 문자열로 변경
	}
	
	
	
}
