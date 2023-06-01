//package com.start.backend.user.service;
//
//public class UserCRUDService {
//
//}
package com.start.backend.user.service;

import com.start.backend.user.dao.UserDao;
import com.start.backend.user.vo.User;
import com.start.backend.user.vo.UserSearch;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserCRUDService implements UserService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private UserDao userDao;

//	@Transactional
//	@Override
//	public String getAccessToken(String code) {
//		return null;
//	}

	@Override
	public User getKakaoUser(User insertUser) {

		User user = new User();
		user = userDao.selectKaKaoUser(insertUser);
		
		return user;
	}
	

	@Override
	public int signUpUser(User user) {
		
		int signUpUser = 0;
		try {
			signUpUser = userDao.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return signUpUser;
	}
	
	@Override
	public User loginUser(User user) {
		User loginUser = userDao.selectLoginUser(user);
		
		return loginUser;
	}
	
	@Override
	public int checkUserId(String userId) {
		int checkUserId = userDao.checkUserId(userId);
		
		log.debug("service : " + checkUserId);
		return checkUserId;
	}
	

	@Override
	public List<User> getUsers(User user) {
		
		List<User> userList = userDao.selectUserList(user);
		
		
		return userList;
	}
	
	@Override
	public int deleteUser(String userId) throws Exception {
		int deleteUser = userDao.deleteUser(userId);
		
		return deleteUser;
	}
	

	@Override
	public List<User> getUserSearch(String keyword) {
		List<User> getUserSearch = userDao.getUserSearch(keyword);
		log.debug(getUserSearch);
		return getUserSearch;
	}
	
	//------------------------------------------------------------

	@Transactional
	@Override
	public User getUser(String userId) {
		User user = userDao.getUser(userId);
		return user;
	}
	
	@Transactional
	@Override
	public User addUser(String userId) {
		User user = userDao.addUser(userId);
		return user;
	}
	
	@Transactional
	@Override
	public void updateUser(String userId) throws Exception {
		
		userDao.updateUser(userId);
	}


	
//	@Transactional
//	@Override
//	public void deleteUser(String userId) throws Exception {
//		
//		userDao.deleteUser(userId);
//	}


}
