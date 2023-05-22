package com.start.backend.searchMap.service;

import com.start.backend.searchMap.vo.ParamCenterAddress;

public interface SearchMapService {

	public String getCenterOne(int centerNum);
	public String getCenterList(int centerNum);
	public String getChildcareEvalOne(int centerNum);
	public String getChildcareViolationOne(int centerNum);
	public String getKidsdataDetailOne(int centerNum);
	public String getKidsdataDetailList(int centerNum);

	public String recommendCentersByAddress(ParamCenterAddress centerAddress);
	public String searchCentersByCenterName(String centerName);
	
	public String getKinderDataByApi(String centerNum);
	public String getPositionByKakaoApi(String centerNum);
	
}
