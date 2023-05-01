package com.start.backend.grade.service;

import com.start.backend.grade.vo.Grade;
import com.start.backend.transactionSample.vo.Sample;

public interface GradeService {

	Grade getGrade(String center_num);

	Sample addUser(String userId);

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;
	
}
