package com.start.backend.information.service;

import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.vo.User;

public interface InformationService {

	Information getInformation(String center_num);

	User addUser(String userId);

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;
	
}
