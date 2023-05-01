package com.start.backend.detail.controller;

import com.start.backend.detail.vo.Detail;
import com.start.backend.information.vo.Information;

public interface DetailController {

	Detail getDetail(String center_num);

	Information addUser(String userId);

    void updateUser(String userId) throws Exception;
    
	void deleteUser(String userId) throws Exception;

	Information get();


	
}