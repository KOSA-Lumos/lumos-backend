package com.start.backend.searchMap.controller;

import org.springframework.web.bind.annotation.PathVariable;

import com.start.backend.searchMap.vo.SearchMap;

public interface SearchMapController {

	// GET methods
	public String getCenterOne(@PathVariable String centerNum, String condition);
	public String getChildcareEvalOne(@PathVariable String centerNum, String condition);
	public String getChildcareViolationOne(@PathVariable String centerNum, String condition);
	public String getKidsdataDetailOne(@PathVariable String centerNum, String condition);
//	public String getMethod2(int searchMapNum, SearchMap searchMap);
//	public String getMethod3(int searchMapNum, SearchMap searchMap);

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
