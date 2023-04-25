package com.start.backend.detail.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.backend.detail.dao.DetailDao;
import com.start.backend.detail.vo.Detail;
import com.start.backend.grade.dao.GradeDao;
import com.start.backend.grade.vo.Grade;
import com.start.backend.information.dao.InformationDao;
import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.dao.UserDao;
import com.start.backend.transactionSample.vo.User;


@Service
public class DetailCRUDService implements DetailService {
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
	public User addUser(String userId) {
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