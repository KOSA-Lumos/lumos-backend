package com.start.backend.grade.dao;

import com.start.backend.grade.vo.Grade;
import com.start.backend.transactionSample.vo.Sample;


public interface GradeDao {

	Grade getGrade(String center_num);

	Sample getUser(String userId);
	
	void insertUser(String userId) throws Exception;

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;


}
