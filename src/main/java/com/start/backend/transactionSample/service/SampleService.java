package com.start.backend.transactionSample.service;

import com.start.backend.transactionSample.vo.Sample;

public interface SampleService {

	Sample getSample(String userId);

	Sample addSample(String userId);

	void updateSample(String userId) throws Exception;

	void deleteSample(String userId) throws Exception;
	
}
