package com.start.backend.transactionSample.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.backend.transactionSample.dao.UserDao;
import com.start.backend.transactionSample.vo.User;


@Service
@PropertySource("classpath:/config/profileImageDirectory.properties")
public class UserCRUDService implements UserService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private User user;

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
	
	@Transactional
	@Override
	public void deleteUser(String userId) throws Exception {
		
		userDao.deleteUser(userId);
	}
}
