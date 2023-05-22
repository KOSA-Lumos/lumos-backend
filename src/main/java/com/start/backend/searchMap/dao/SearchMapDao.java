package com.start.backend.searchMap.dao;

import java.util.List;

import com.start.backend.searchMap.vo.SMapCenter;
import com.start.backend.searchMap.vo.SMapCenterKidsdataDetail;
import com.start.backend.searchMap.vo.SMapChildcareEval;
import com.start.backend.searchMap.vo.SMapChildcareViolation;
import com.start.backend.searchMap.vo.SMapKidsdataDetail;
import com.start.backend.searchMap.vo.SMapSidocode;

public interface SearchMapDao {

	public SMapCenter getCenterOne(int centerNum);
	public List<SMapCenter> getCenterList(int centerNum);
	public List<SMapCenter> getCenterListByCenterName(String centerName);
	public List<SMapCenter> getCenterListByStateCity(SMapCenter keyword);
	public SMapChildcareEval getChildcareEvalOne(int centerNum);
	public SMapChildcareViolation getChildcareViolationOne(int centerNum);
	public SMapKidsdataDetail getKidsdataDetailOne(int centerNum);
	public List<SMapKidsdataDetail> getKidsdataDetailList(int centerNum);
	public List<SMapKidsdataDetail> getKidsdataDetailListByStateCityLatLng(SMapKidsdataDetail keywordKidsdataDetail);
	public SMapSidocode getSidocodeOne(SMapSidocode sidocode);
	
}
