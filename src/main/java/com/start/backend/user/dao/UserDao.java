//package com.start.backend.user.dao;
//
//public interface UserDao {
//
//}
package com.start.backend.user.dao;

import com.start.backend.user.vo.User;


public interface UserDao {
	
	User selectKaKaoUser(User user);

	User selectLoginUser(User user);

	User addUser(String userId);

	User getUser(String userId);
	
	int insertUser(User user) throws Exception;

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;
}
