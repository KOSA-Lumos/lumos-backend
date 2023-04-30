package com.start.backend.transactionSample.dao;

import com.start.backend.transactionSample.vo.Sample;


public interface SampleDao {

	Sample addSample(String userId);

	Sample getSample(String userId);
	
	void insertSample(String userId) throws Exception;

	void updateSample(String userId) throws Exception;

	void deleteSample(String userId) throws Exception;

}
