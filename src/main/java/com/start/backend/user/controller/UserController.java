//package com.start.backend.user.controller;
//
//public interface UserController {
//
//}
package com.start.backend.user.controller;

import java.util.List;
import java.util.Map;

import com.start.backend.user.vo.User;
public interface UserController {

	User getkakaoLogin(String userId);

	User getUser(String userId);
	
	int signUp(User user);

	Map<String, Boolean> checkUserId(String userId);
	
	List<User> getUsers(User user);
	
	int deleteUser(String userId) throws Exception;
	
	List<User> getUserSearch(String keyword);
	
	
	User addUser(String userId);

//    void updateUser(String userId) throws Exception;
    
//	void deleteUser(String userId) throws Exception;


}