package com.start.backend.searchMap.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.start.backend.searchMap.dao.SearchMapDao;
import com.start.backend.searchMap.vo.SMapCenter;
import com.start.backend.searchMap.vo.SMapChildcareEval;
import com.start.backend.searchMap.vo.SMapChildcareViolation;
import com.start.backend.searchMap.vo.SMapKidsdataDetail;

@Service
public class SearchMapServiceImpl implements SearchMapService {

	private Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private SearchMapDao searchMapDao;
	
	@Override
	public String getCenterOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		SMapCenter center = searchMapDao.getCenterOne(centerNum);
		log.debug(center);
		Gson gson = new Gson();
		String json = gson.toJson(center);
		log.debug(json);
		return json;
	}

	@Override
	public String getCenterList() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		List<SMapCenter> centerList = searchMapDao.getCenterList();
		log.debug(centerList);
		Gson gson = new Gson();
		String json = gson.toJson(centerList);
		log.debug(json);
		return json;
	}

	@Override
	public String getChildcareEvalOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		SMapChildcareEval childcareEval = searchMapDao.getChildcareEvalOne(centerNum);
		log.debug(childcareEval);
		Gson gson = new Gson();
		String json = gson.toJson(childcareEval);
		log.debug(json);
		return json;
	}

	@Override
	public String getChildcareViolationOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		SMapChildcareViolation childcareViolation = searchMapDao.getChildcareViolationOne(centerNum);
		log.debug(childcareViolation);
		Gson gson = new Gson();
		String json = gson.toJson(childcareViolation);
		log.debug(json);
		return json;
	}

	@Override
	public String getKidsdataDetailOne(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		SMapKidsdataDetail kidsdataDetail = searchMapDao.getKidsdataDetailOne(centerNum);
		log.debug(kidsdataDetail);
		Gson gson = new Gson();
		String json = gson.toJson(kidsdataDetail);
		log.debug(json);
		return json;
	}

	@Override
	public String getKidsdataDetailList(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		List<SMapKidsdataDetail> kidsdataDetailList = searchMapDao.getKidsdataDetailList(centerNum);
		log.debug(kidsdataDetailList);
		Gson gson = new Gson();
		String json = gson.toJson(kidsdataDetailList);
		log.debug(json);
		return json;
	}
	
}
