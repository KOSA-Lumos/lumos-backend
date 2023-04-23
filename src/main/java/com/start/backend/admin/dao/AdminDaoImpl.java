package com.start.backend.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.transactionSample.vo.User;


@Repository
public class AdminDaoImpl implements AdminDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;

	@Override
	public User addUser(String userId) {
		
		User user = session.selectOne("User.addUser", userId);
		
		return user;
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

