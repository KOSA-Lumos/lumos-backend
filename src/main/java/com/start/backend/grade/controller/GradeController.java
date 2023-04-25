package com.start.backend.grade.controller;

import com.start.backend.information.vo.Information;

public interface GradeController {

	Information getInformation(String userId);

	Information addUser(String userId);

    void updateUser(String userId) throws Exception;
    
	void deleteUser(String userId) throws Exception;

	Information get();
	
}