package com.start.backend.kindergartenDetail.controller;

import com.start.backend.transactionSample.vo.User;

public interface KindergartenDetailController {

	User getUser(String userId);

	User addUser(String userId);

    void updateUser(String userId) throws Exception;
    
	void deleteUser(String userId) throws Exception;
	
}