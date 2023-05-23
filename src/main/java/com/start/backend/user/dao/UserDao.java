//package com.start.backend.user.dao;
//
//public interface UserDao {
//
//}
package com.start.backend.user.dao;

import java.util.List;

import com.start.backend.user.vo.User;


public interface UserDao {
	
	User selectKaKaoUser(User user);

	User selectLoginUser(User user);
	
	int insertUser(User user) throws Exception;
	
	int checkUserId(String userId);
	
	List<User> selectUserList(User user);
	
	int deleteUser(String userId) throws Exception;

	User addUser(String userId);

	User getUser(String userId);

	void updateUser(String userId) throws Exception;

	List<User> getUserSearch(String keyword);

//	void deleteUser(String userId) throws Exception;


}
