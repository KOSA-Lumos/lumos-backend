//package com.start.backend.user.service;
//
//public interface UserService {
//
//}
package com.start.backend.user.service;

import java.util.List;
import java.util.Map;

import com.start.backend.user.vo.User;
import com.start.backend.user.vo.UserSearch;

public interface UserService {

//	String getAccessToken(String code);

	User getKakaoUser(User user);
	
	int signUpUser(User user);

	User loginUser(User user);
	
	int checkUserId(String userId);
	
	List<User> getUsers(User user);
	
	int deleteUser(String userId) throws Exception;

	User getUser(String userId);

	User addUser(String userId);

	void updateUser(String userId) throws Exception;

	List<User> getUserSearch(String keyword);


//	void deleteUser(String userId) throws Exception;



}
