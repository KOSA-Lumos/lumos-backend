package com.start.backend.information.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.information.vo.Information;
import com.start.backend.transactionSample.vo.Sample;


@Repository
public class InformationDaoImpl implements InformationDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;

	@Override
	public Information getInformation(String center_num) {
		
		log.debug("getInformationDao() 메소드 실행 중!");
		
		Information information = session.selectOne("Information.getInformation", center_num);
		
		return information;
	}
	
	@Override
	public Sample getUser(String userId) {
		
		Sample user = session.selectOne("Sample.getUser", userId);
		
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
		
		session.insert("Sample.insertUser", userId);
		
	}
	
	@Override
	public void updateUser(String userId) throws Exception {
		
		session.update("Sample.updateUser", userId);
		
	}
	
	@Override
	public void deleteUser(String userId) throws Exception {
		
		session.delete("Sample.deleteUser", userId);
		
	}
	
}

