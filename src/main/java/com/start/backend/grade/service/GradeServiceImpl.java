package com.start.backend.grade.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.backend.grade.dao.GradeDao;
import com.start.backend.grade.vo.Grade;
import com.start.backend.transactionSample.vo.Sample;


@Service
public class GradeServiceImpl implements GradeService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private GradeDao gradeDao;
	
	@Transactional
	@Override
	public Grade getGrade(String center_num) {
		
		Grade grade = gradeDao.getGrade(center_num);
		
		return grade;
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
