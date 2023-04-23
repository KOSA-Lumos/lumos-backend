package com.start.backend.transactionSample.service;

import com.start.backend.transactionSample.vo.User;

public interface UserService {

	User getUser(String userId);

	User addUser(String userId);

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;
	
}
