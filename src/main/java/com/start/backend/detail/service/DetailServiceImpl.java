package com.start.backend.detail.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.backend.detail.dao.DetailDao;
import com.start.backend.detail.vo.Detail;
import com.start.backend.transactionSample.vo.Sample;


@Service
public class DetailServiceImpl implements DetailService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private DetailDao detailDao;
	
	@Transactional
	@Override
	public Detail getDetail(String center_num) {
		
		Detail detail = detailDao.getDetail(center_num);
		
		return detail;
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
//	public Sample addUser(String userId) {
//		Sample user = userDao.addUser(userId);
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
