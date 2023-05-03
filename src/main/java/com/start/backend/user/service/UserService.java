//package com.start.backend.user.service;
//
//public interface UserService {
//
//}
package com.start.backend.user.service;

import com.start.backend.user.vo.User;

public interface UserService {

//	String getAccessToken(String code);

	User getKakaoUser(User user);
	
	int signUpUser(User user);

	User loginUser(User user);

	User getUser(String userId);

	User addUser(String userId);

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;


}
