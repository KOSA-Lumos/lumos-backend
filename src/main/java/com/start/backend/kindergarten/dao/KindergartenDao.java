package com.start.backend.kindergarten.dao;

import com.start.backend.transactionSample.vo.User;


public interface KindergartenDao {

	User addUser(String userId);

	User getUser(String userId);
	
	void insertUser(String userId) throws Exception;

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;

}
