package com.start.backend.searchMap.controller;

import org.springframework.web.bind.annotation.PathVariable;

import com.start.backend.searchMap.vo.SearchMap;

public interface SearchMapController {

	// GET methods
	public String getCenterOne(@PathVariable String centerNum, String condition);
	public String getCenterList(@PathVariable String centerNum, String condition);
	public String getChildcareEvalOne(@PathVariable String centerNum, String condition);
	public String getChildcareViolationOne(@PathVariable String centerNum, String condition);
	public String getKidsdataDetailOne(@PathVariable String centerNum, String condition);
	public String getKidsdataDetailList(@PathVariable String centerNum, String condition);
	
	public String searchCentersByCenterName(@PathVariable String centerNum, String condition);

	public String getKinderDataByApi(@PathVariable String centerNum);
	public String getPositionByKakaoApi(@PathVariable String centerNum);

//	// PUT methods
//	String addSearchMap(String searchMap);
//
//	// POST methods
//	String editSearchMapName(String path, SearchMap searchMap);
//	String editSearchMapContents(String searchMapContents);
//
//	// DELETE methods
//	String delSearchMap(String searchMapId) throws Exception;
	
}
