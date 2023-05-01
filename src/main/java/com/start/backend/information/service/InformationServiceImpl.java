package com.start.backend.information.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.backend.information.dao.InformationDao;
import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.vo.Sample;


@Service
public class InformationServiceImpl implements InformationService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private InformationDao informationDao;
	
	@Transactional
	@Override
	public Information getInformation(String center_num) {
		log.debug("dsdsdsd");
		
		Information information = informationDao.getInformation(center_num);
		
		return information;
	}

	@Override
	public Sample addUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
//	@Transactional
//	@Override
//	public User addUser(String userId) {
//		User user = userDao.addUser(userId);
//		return user;
//	}
//	
//	@Transactional
//	@Override
//	public void updateUser(String userId) throws Exception {
//		
//		userDao.updateUser(userId);
//	}
//	
//	@Transactional
//	@Override
//	public void deleteUser(String userId) throws Exception {
//		
//		userDao.deleteUser(userId);
//	}
}
