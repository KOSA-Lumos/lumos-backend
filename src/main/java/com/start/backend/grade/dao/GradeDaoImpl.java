package com.start.backend.grade.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.grade.vo.Grade;
import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.vo.User;


@Repository
public class GradeDaoImpl implements GradeDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;

	@Override
	public Grade getGrade(String center_num) {
		
		log.debug("getGrade() 메소드 실행 중!");
		
		Grade grade = session.selectOne("Grade.getGrade", center_num);
		
		return grade;
	}
	
	@Override
	public User getUser(String userId) {
		
		User user = session.selectOne("User.getUser", userId);
		
		return user;
	}
	
//	@Override
//	public List<Object> getUser(String userId) {
//		
//		List<Object> user = session.selectList("User.*****", userId);
//		
//		return user;
//	}

	@Override
	public void insertUser(String userId) throws Exception {
		
		session.insert("User.insertUser", userId);
		
	}
	
	@Override
	public void updateUser(String userId) throws Exception {
		
		session.update("User.updateUser", userId);
		
	}
	
	@Override
	public void deleteUser(String userId) throws Exception {
		
		session.delete("User.deleteUser", userId);
		
	}
	
}

