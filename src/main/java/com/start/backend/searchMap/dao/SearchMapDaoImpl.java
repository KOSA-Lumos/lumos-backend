package com.start.backend.searchMap.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.searchMap.vo.SMapCenter;
import com.start.backend.searchMap.vo.SMapChildcareEval;
import com.start.backend.searchMap.vo.SMapChildcareViolation;
import com.start.backend.searchMap.vo.SMapKidsdataDetail;

@Repository
public class SearchMapDaoImpl implements SearchMapDao {

	private Logger log = LogManager.getLogger("case3");
	private String mapperNamespace = "SearchMap";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public SMapCenter getCenterOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@@ " + methodName + " 실행");
		
		String queryId = methodName;
		SMapCenter result = session.selectOne(mapperNamespace + "." + queryId, centerNum);
		return result;
	}

	@Override
	public SMapChildcareEval getChildcareEvalOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@@ " + methodName + " 실행");
		
		String queryId = methodName;
		SMapChildcareEval result = session.selectOne(mapperNamespace + "." + queryId, centerNum);
		return result;
	}

	@Override
	public SMapChildcareViolation getChildcareViolationOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@@ " + methodName + " 실행");
		
		String queryId = methodName;
		SMapChildcareViolation result = session.selectOne(mapperNamespace + "." + queryId, centerNum);
		return result;
	}

	@Override
	public SMapKidsdataDetail getKidsdataDetailOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@@ " + methodName + " 실행");
		
		String queryId = methodName;
		SMapKidsdataDetail result = session.selectOne(mapperNamespace + "." + queryId, centerNum);
		return result;
	}

}
