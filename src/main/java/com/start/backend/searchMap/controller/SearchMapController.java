package com.start.backend.searchMap.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.start.backend.favorite.vo.Favorite;
import com.start.backend.searchMap.vo.ParamCenterAddress;
import com.start.backend.searchMap.vo.SearchMap;
import com.start.backend.user.vo.User;

public interface SearchMapController {

	// GET methods
	public String getCenterOne(@PathVariable String centerNum, String condition);
	public String getCenterList(@PathVariable String centerNum, String condition);
	public String getChildcareEvalOne(@PathVariable String centerNum, String condition);
	public String getChildcareViolationOne(@PathVariable String centerNum, String condition);
	public String getKidsdataDetailOne(@PathVariable String centerNum, String condition);
	public String getKidsdataDetailList(@PathVariable String centerNum, String condition);

	public String recommendCentersByAddress(@ModelAttribute ParamCenterAddress centerAddress);
	public String searchCentersByCenterName(@PathVariable String centerNum, String condition);

	public String getKinderDataByApi(@PathVariable String centerNum);
	public String getPositionByKakaoApi(@PathVariable String centerNum);
	
	int addFavorite(@RequestBody Favorite favorite);
	List<Favorite> isFavorite(@RequestBody Favorite favorite);
	List<Favorite> removeFavorite(Favorite favorite);

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
