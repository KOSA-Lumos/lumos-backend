package com.start.backend.detail.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.detail.vo.Detail;
import com.start.backend.transactionSample.vo.Sample;


@Repository
public class DetailDaoImpl implements DetailDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;

	@Override
	public Detail getDetail(String center_num) {
		
		log.debug("getDetail() 메소드 실행 중!");
		
		Detail detail = session.selectOne("Detail.getDetail", center_num);
		
		return detail;
	}
	
	@Override
	public Sample getUser(String userId) {
		
		Sample user = session.selectOne("Sample.getUser", userId);
		
		return user;
	}
	
//	@Override
//	public List<Object> getUser(String userId) {
//		
//		List<Object> user = session.selectList("Sample.*****", userId);
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

