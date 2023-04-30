package com.start.backend.transactionSample.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.backend.transactionSample.dao.SampleDao;
import com.start.backend.transactionSample.vo.Sample;


@Service
public class SampleServiceImpl implements SampleService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SampleDao userDao;
	
	@Transactional
	@Override
	public Sample getSample(String userId) {
		Sample user = userDao.getSample(userId);
		return user;
	}
	
	@Transactional
	@Override
	public Sample addSample(String userId) {
		Sample user = userDao.addSample(userId);
		return user;
	}
	
	@Transactional
	@Override
	public void updateSample(String userId) throws Exception {
		
		userDao.updateSample(userId);
	}
	
	@Transactional
	@Override
	public void deleteSample(String userId) throws Exception {
		
		userDao.deleteSample(userId);
	}
}
