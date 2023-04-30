package com.start.backend.detail.service;

import com.start.backend.detail.vo.Detail;
import com.start.backend.grade.vo.Grade;
import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.vo.Sample;

public interface DetailService {

	Detail getDetail(String center_num);

	Sample addUser(String userId);

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;

	
}
