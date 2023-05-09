package com.start.backend.searchMap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.searchMap.service.SearchMapService;
import com.start.backend.searchMap.vo.SearchMap;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(value="/searchMap", produces = "application/json; charset=utf-8")
public class SearchMapControllerImpl implements SearchMapController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SearchMapService searchMapService;

	
	
	
	
	// Constructors
	public SearchMapControllerImpl(SearchMapService searchMapService) {
		this.searchMapService = searchMapService;
	}

	
	
	
	
	// GET methods
	@Override
	@GetMapping(value="/center/{centerNum}")
	public String getCenterOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getCenterOne(cNum);
		return json;
	}

	@Override
	@GetMapping(value="/center/list/{centerNum}")
	public String getCenterList(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getCenterList(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/childcare_eval/{centerNum}")
	public String getChildcareEvalOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getChildcareEvalOne(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/childcare_violation/{centerNum}")
	public String getChildcareViolationOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getChildcareViolationOne(14);
		return json;
	}
	
	@Override
	@GetMapping(value="/kidsdata_detail/{centerNum}")
	public String getKidsdataDetailOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getKidsdataDetailOne(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/kidsdata_detail/list/{centerNum}")
	public String getKidsdataDetailList(String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getKidsdataDetailList(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/center/list/name/{centerName}")
	public String searchCentersByCenterName(String centerName, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		String json = searchMapService.searchCentersByCenterName(centerName);
		return json;
	}
	
	
	
	
	
	
//	// PUT methods
//	@Override
//	@PutMapping(value="/newsearchMap")
//	public String addSearchMap(String searchMap) {
//		// TODO Auto-generated method stub
//		log.debug("@@ addSearchMap 실행");
//		return null;
//	}
//
//	
//	
//	// POST methods
//	@Override
//	@PostMapping(value="/searchMap/postmethod1")
//	public String editSearchMapName(String path, SearchMap searchMap) {
//		// TODO Auto-generated method stub
//		log.debug("@@ editSearchMapName 실행");
//		return null;
//	}
//
//	@Override
//	@PostMapping(value="/searchMap/postmethod2")
//	public String editSearchMapContents(String searchMapContents) {
//		// TODO Auto-generated method stub
//		log.debug("@@ editSearchMapContents 실행");
//		return null;
//	}
//
//	
//	
//	// DELETE methods
//	@Override
//	@DeleteMapping(value="/searchMap/delmethod")
//	public String delSearchMap(String searchMapId) throws Exception {
//		// TODO Auto-generated method stub
//		log.debug("@@ delSearchMap 실행");
//		return null;
//	}

}
