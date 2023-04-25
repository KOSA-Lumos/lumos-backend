package com.start.backend.grade.dao;

import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.vo.User;


public interface GradeDao {

	Information getInformation(String center_num);

	User getUser(String userId);
	
	void insertUser(String userId) throws Exception;

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;

}
