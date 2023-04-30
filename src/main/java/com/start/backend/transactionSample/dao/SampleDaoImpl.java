package com.start.backend.transactionSample.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.transactionSample.vo.Sample;


@Repository
public class SampleDaoImpl implements SampleDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;

	@Override
	public Sample addSample(String userId) {
		
		Sample user = session.selectOne("Sample.addSample", userId);
		
		return user;
	}
	
	@Override
	public Sample getSample(String userId) {
		
		Sample user = session.selectOne("Sample.getSample", userId);
		
		return user;
	}
	
//	@Override
//	public List<Object> getSample(String userId) {
//		
//		List<Object> user = session.selectList("Sample.*****", userId);
//		
//		return user;
//	}

	@Override
	public void insertSample(String userId) throws Exception {
		
		session.insert("Sample.insertSample", userId);
		
	}
	
	@Override
	public void updateSample(String userId) throws Exception {
		
		session.update("Sample.updateSample", userId);
		
	}
	
	@Override
	public void deleteSample(String userId) throws Exception {
		
		session.delete("Sample.deleteSample", userId);
		
	}
	
}

