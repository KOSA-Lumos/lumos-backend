//package com.start.backend.user.controller;
//
//public interface UserController {
//
//}
package com.start.backend.user.controller;

import com.start.backend.user.vo.User;
public interface UserController {

	User getkakaoLogin(String userId);

	User getUser(String userId);

	User addUser(String userId);

    void updateUser(String userId) throws Exception;
    
	void deleteUser(String userId) throws Exception;

	int signUp(User user);

}