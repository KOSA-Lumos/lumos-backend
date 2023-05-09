package com.start.backend.searchMap.dao;

import java.util.List;

import com.start.backend.searchMap.vo.SMapCenter;
import com.start.backend.searchMap.vo.SMapChildcareEval;
import com.start.backend.searchMap.vo.SMapChildcareViolation;
import com.start.backend.searchMap.vo.SMapKidsdataDetail;

public interface SearchMapDao {

	public SMapCenter getCenterOne(int centerNum);
	public List<SMapCenter> getCenterList(int centerNum);
	public SMapChildcareEval getChildcareEvalOne(int centerNum);
	public SMapChildcareViolation getChildcareViolationOne(int centerNum);
	public SMapKidsdataDetail getKidsdataDetailOne(int centerNum);
	public List<SMapKidsdataDetail> getKidsdataDetailList(int centerNum);
	
}
