package com.start.backend.searchMap.service;

public interface SearchMapService {

	public String getCenterOne(int centerNum);
	public String getCenterList();
	public String getChildcareEvalOne(int centerNum);
	public String getChildcareViolationOne(int centerNum);
	public String getKidsdataDetailOne(int centerNum);
	public String getKidsdataDetailList(int centerNum);
	
}
