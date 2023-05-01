package com.start.backend.detail.dao;

import com.start.backend.detail.vo.Detail;
import com.start.backend.transactionSample.vo.Sample;


public interface DetailDao {

	Detail getDetail(String center_num);

	Sample getUser(String userId);
	
	void insertUser(String userId) throws Exception;

	void updateUser(String userId) throws Exception;

	void deleteUser(String userId) throws Exception;

}
