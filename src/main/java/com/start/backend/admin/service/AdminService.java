package com.start.backend.admin.service;

import com.start.backend.transactionSample.vo.User;

public interface AdminService {

	User getUser(String userId);

	User addUser(String userId);

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;
	
}
