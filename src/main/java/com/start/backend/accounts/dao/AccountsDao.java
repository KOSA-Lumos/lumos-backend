package com.start.backend.accounts.dao;

import com.start.backend.transactionSample.vo.User;


public interface AccountsDao {

	User addUser(String userId);

	User getUser(String userId);
	
	void insertUser(String userId) throws Exception;

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;

}
