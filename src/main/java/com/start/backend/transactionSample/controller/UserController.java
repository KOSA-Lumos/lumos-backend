package com.start.backend.transactionSample.controller;

import com.start.backend.transactionSample.vo.User;

public interface UserController {

	User getUser(String userId);

	User addUser(String userId);

    void updateUser(String userId) throws Exception;
    
	void deleteUser(String userId) throws Exception;
	
}