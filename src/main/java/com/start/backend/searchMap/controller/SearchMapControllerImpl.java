package com.start.backend.searchMap.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.favorite.service.FavoriteService;
import com.start.backend.favorite.vo.Favorite;
import com.start.backend.searchMap.service.SearchMapService;
import com.start.backend.searchMap.vo.ParamCenterAddress;
import com.start.backend.searchMap.vo.SearchMap;
import com.start.backend.user.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(value="/searchMap", produces = "application/json; charset=utf-8")
public class SearchMapControllerImpl implements SearchMapController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SearchMapService searchMapService;
	@Autowired
	private FavoriteService fvService;

	
	
	
	
	// Constructors
	public SearchMapControllerImpl(SearchMapService searchMapService) {
		this.searchMapService = searchMapService;
	}

	
	
	
	
	// GET methods
	@Override
	@GetMapping(value="/skeleton/center/{centerNum}")
	public String getCenterOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getCenterOne(cNum);
		return json;
	}

	@Override
	@GetMapping(value="/skeleton/center/list/{centerNum}")
	public String getCenterList(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getCenterList(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/skeleton/childcare_eval/{centerNum}")
	public String getChildcareEvalOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getChildcareEvalOne(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/skeleton/childcare_violation/{centerNum}")
	public String getChildcareViolationOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getChildcareViolationOne(14);
		return json;
	}
	
	@Override
	@GetMapping(value="/skeleton/kidsdata_detail/{centerNum}")
	public String getKidsdataDetailOne(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getKidsdataDetailOne(cNum);
		return json;
	}
	
	@Override
	@GetMapping(value="/skeleton/kidsdata_detail/list/{centerNum}")
	public String getKidsdataDetailList(@PathVariable String centerNum, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		int cNum = Integer.parseInt(centerNum);
		String json = searchMapService.getKidsdataDetailList(cNum);
		return json;
	}

	// 내 위치 기반 센터 추천받기
	// 수신 정보 : 위도, 경도, 주소
	@Override
	@GetMapping(value="/center/list/address")
	public String recommendCentersByAddress(@ModelAttribute ParamCenterAddress centerAddress) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		System.out.println(centerAddress);
//		String cAddress = "서울특별시 강남구 역삼동";
		String json = searchMapService.recommendCentersByAddress(centerAddress);
		return json;
	}
	
	// 이름으로 센터 Like 검색
	@Override
	@GetMapping(value="/center/list/name/{centerName}")
	public String searchCentersByCenterName(@PathVariable String centerName, String condition) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		String json = searchMapService.searchCentersByCenterName(centerName);
		return json;
	}
	
	// 유치원 API 가져오기 테스트용 method
	@Override
	@GetMapping(value="/apicalltest/kinder/{centerNum}")
	public String getKinderDataByApi(@PathVariable String centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		String json = searchMapService.getKinderDataByApi(centerNum);
		return json;
	}
	
	// Kakao API 가져오기 테스트용 method
	@Override
	@GetMapping(value="/apicalltest/kakao/{centerNum}")
	public String getPositionByKakaoApi(@PathVariable String centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@ " + methodName + " 실행");
		
		String json = searchMapService.getPositionByKakaoApi(centerNum);
		return json;
	}
	
//	@Override
//	@PostMapping(value="/favorite")
//	public int addFavorite(@RequestBody Favorite favorite) {
//		log.debug(favorite);
////		Favorite favorite = new Favorite();
////		favorite.setUserId(userId);
////		favorite.setCenterNum(centerNum);
//		int result = fvService.addFavorite(favorite);
//		
//		return result;
//	}
	
	@Override
	@PostMapping(value="/favorite")
	public List<Favorite> isFavorite(@RequestBody Favorite favorite) {
		log.debug(favorite);
		
		List<Favorite> favoriteList = fvService.isFavorite(favorite);
		log.debug("2"+favorite);
//		int result = fvService.addFavorite(favorite);
		
		if(favoriteList.isEmpty()) {
			fvService.addFavorite(favorite);
			log.debug("찜하기");
		} else {
			fvService.removeFavorite(favorite);
			log.debug("찜제거하기");
			log.debug("3"+favorite);
		}
		
		
		List<Favorite> newFavoriteList = fvService.isFavorite(favorite);
		
		
		return newFavoriteList;
	}
	

	@Override
	@PostMapping(value="/delete-favorite")
	@Transactional
	public List<Favorite> removeFavorite(@RequestBody Favorite favorite) {
		log.debug(favorite);

		fvService.removeFavorite(favorite);
		List<Favorite> newFavoriteList = fvService.isFavorite(favorite);
		
		
		return newFavoriteList;
	}
	



	@Override
	public int addFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		return 0;
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
