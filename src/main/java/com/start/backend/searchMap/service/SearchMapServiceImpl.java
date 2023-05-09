package com.start.backend.searchMap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.start.backend.searchMap.dao.SearchMapDao;
import com.start.backend.searchMap.vo.SMapCenter;
import com.start.backend.searchMap.vo.SMapCenterKidsdataDetail;
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
	public String getCenterList(int centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		// 보육시설 기본 정보(Center 테이블 데이터) 가져오기
		List<SMapCenter> centerList = searchMapDao.getCenterList(centerNum);
		
		// 어린이집 상세 데이터(Kidsdata_detail 테이블 데이터) 가져오기
		List<SMapKidsdataDetail> kidsdataDetailList = searchMapDao.getKidsdataDetailList(centerNum);
		
		// 유치원 상세 데이터(유치원 API 데이터) 가져오기
		
		
		// 데이터 결합
		HashMap<Integer, SMapCenterKidsdataDetail> centerKidsdataDetailMap = new HashMap<Integer, SMapCenterKidsdataDetail>();
		for (SMapCenter item : centerList) {
			SMapCenterKidsdataDetail temp = new SMapCenterKidsdataDetail();
			temp.setCenterNum(item.getCenterNum());
			temp.setCenterName(item.getCenterName());
			temp.setCenterCategory(item.getCenterCategory());
			temp.setCenterState(item.getCenterState());
			temp.setCenterCity(item.getCenterCity());
			temp.setCenterType(item.getCenterType());
			temp.setCenterExtendcare(item.getCenterExtendcare());

			centerKidsdataDetailMap.put(temp.getCenterNum(), temp);
		}
		for (SMapKidsdataDetail item : kidsdataDetailList) {
			SMapCenterKidsdataDetail temp = centerKidsdataDetailMap.get(item.getCenterNum());
			
			if (temp != null) {
				temp.setCenterDetailState(item.getCenterDetailState());
				temp.setCenterDetailCity(item.getCenterDetailCity());
				temp.setCenterDetailBame(item.getCenterDetailBame());
				temp.setCenterDetailClassification(item.getCenterDetailClassification());
				temp.setCenterDetailCenteropen(item.getCenterDetailCenteropen());
				temp.setCenterDetailOfficenumber(item.getCenterDetailOfficenumber());
				temp.setCenterDetailAddress(item.getCenterDetailAddress());
				temp.setCenterDetailPhone(item.getCenterDetailPhone());
				temp.setCenterDetailFax(item.getCenterDetailFax());
				temp.setCenterDetailRoomcount(item.getCenterDetailRoomcount());
				temp.setCenterDetailRoomsize(item.getCenterDetailRoomsize());
				temp.setCenterDetailPlaygroundcount(item.getCenterDetailPlaygroundcount());
				temp.setCenterDetailTeachercount(item.getCenterDetailTeachercount());
				temp.setCenterDetailRegularperson(item.getCenterDetailRegularperson());
				temp.setCenterDetailCurrentperson(item.getCenterDetailCurrentperson());
				temp.setCenterDetailLatitude(item.getCenterDetailLatitude());
				temp.setCenterDetailLongitude(item.getCenterDetailLongitude());
				temp.setCenterDetailVehicle(item.getCenterDetailVehicle());
				temp.setCenterDetailHompage(item.getCenterDetailHompage());
				temp.setCenterDetailEstablish(item.getCenterDetailEstablish());
				
				centerKidsdataDetailMap.put(temp.getCenterNum(), temp);
			}
		}
		
		// 객체가 아니라 배열로 return 해주기 위해 map을 list로 변경
		List<SMapCenterKidsdataDetail> centerKidsdataDetailList = new ArrayList<SMapCenterKidsdataDetail>(centerKidsdataDetailMap.values());

		// json 데이터로 변경
		Gson gson = new Gson();
		String json = gson.toJson(centerKidsdataDetailList);
		log.debug(json);
			// log(map) 의 출력
			// {"6":{"centerNum":6,"centerName":"SGI서울보증 어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SGI서울보증 어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3128,"centerDetailAddress":"서울특별시 종로구 김상옥로 29 2층","centerDetailPhone":"02-3671-7051","centerDetailFax":"02-3671-7053","centerDetailRoomcount":4,"centerDetailRoomsize":178,"centerDetailPlaygroundcount":"0","centerDetailTeachercount":13,"centerDetailRegularperson":49,"centerDetailCurrentperson":25,"centerDetailLatitude":37.573628643388055,"centerDetailLongitude":127.00103012788806,"centerDetailVehicle":"미운영","centerDetailHompage":"https://www.puruni.com/sgic","centerDetailEstablish":"2016-02-22"},"7":{"centerNum":7,"centerName":"SK ecoplant 행복날개어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SK ecoplant 행복날개어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3143,"centerDetailAddress":"서울특별시 종로구 율곡로2길 19 SK ecoplant 행복날개어린이집","centerDetailPhone":"02-6395-0202","centerDetailFax":"02-6395-0203","centerDetailRoomcount":4,"centerDetailRoomsize":177,"centerDetailPlaygroundcount":"1","centerDetailTeachercount":15,"centerDetailRegularperson":49,"centerDetailCurrentperson":30,"centerDetailLatitude":37.574433532681724,"centerDetailLongitude":126.98114619280257,"centerDetailVehicle":"미운영","centerDetailHompage":"www.puruni.com/skec","centerDetailEstablish":"2013-08-27"},"8":{"centerNum":8,"centerName":"SK네트웍스새싹어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SK네트웍스새싹어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3190,"centerDetailAddress":"서울특별시 종로구 청계천로 85 삼일빌딩, 3층","centerDetailPhone":"070-7800-2115","centerDetailFax":"070-7800-2118","centerDetailRoomcount":4,"centerDetailRoomsize":208,"centerDetailPlaygroundcount":"0","centerDetailTeachercount":9,"centerDetailRegularperson":49,"centerDetailCurrentperson":9,"centerDetailLatitude":37.56505624,"centerDetailLongitude":126.9830142,"centerDetailVehicle":"미운영","centerDetailHompage":"http://moamom016.kiznet.co.kr","centerDetailEstablish":"2009-01-29"},"9":{"centerNum":9,"centerName":"SK행복어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SK행복어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3188,"centerDetailAddress":"서울특별시 종로구 종로 26 SK서린동빌딩 2층","centerDetailPhone":"02-2121-1611","centerDetailFax":"02-2121-1610","centerDetailRoomcount":7,"centerDetailRoomsize":364,"centerDetailPlaygroundcount":"1","centerDetailTeachercount":30,"centerDetailRegularperson":99,"centerDetailCurrentperson":73,"centerDetailLatitude":37.56967307,"centerDetailLongitude":126.980289,"centerDetailVehicle":"미운영","centerDetailHompage":"www.puruni.com/skseoul","centerDetailEstablish":"2007-09-14"}} == 2023-05-09 11:26:10,203
			// log(list) 의 출력
			// [{"centerNum":6,"centerName":"SGI서울보증 어린이집","centerCategory":"어린이집","centerStat
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
