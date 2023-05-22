package com.start.backend.searchMap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.start.backend.searchMap.api.KakaoApiAddressDocuments;
import com.start.backend.searchMap.api.KakaoApiAddressResponse;
import com.start.backend.searchMap.api.KinderApiBasicInfo2;
import com.start.backend.searchMap.api.KinderApiBasicInfo2Response;
import com.start.backend.searchMap.api.KinderApiClassArea;
import com.start.backend.searchMap.api.KinderApiClassAreaResponse;
import com.start.backend.searchMap.api.KinderApiSafetyEdu;
import com.start.backend.searchMap.api.KinderApiSafetyEduResponse;
import com.start.backend.searchMap.api.KinderApiSchoolBus;
import com.start.backend.searchMap.api.KinderApiSchoolBusResponse;
import com.start.backend.searchMap.api.KinderApiTeachersInfo;
import com.start.backend.searchMap.api.KinderApiTeachersInfoResponse;
import com.start.backend.searchMap.dao.SearchMapDao;
import com.start.backend.searchMap.vo.ParamCenterAddress;
import com.start.backend.searchMap.vo.SMapCenter;
import com.start.backend.searchMap.vo.SMapCenterKidsdataDetail;
import com.start.backend.searchMap.vo.SMapChildcareEval;
import com.start.backend.searchMap.vo.SMapChildcareViolation;
import com.start.backend.searchMap.vo.SMapKidsdataDetail;
import com.start.backend.searchMap.vo.SMapSidocode;

@Service
@PropertySource("classpath:config/application.properties")
public class SearchMapServiceImpl implements SearchMapService {

	private Logger log = LogManager.getLogger("case3");
    RestTemplate restTemplate = new RestTemplate(); // 오픈 API 접속을 위한 RestTemplate 인스턴스 생성
    Gson gson = new Gson(); // JSON 데이터 활용을 위한 GSON 객체 생성
	
	@Autowired
	private SearchMapDao searchMapDao;
	
	// 외부 파일에 API key를 저장하기 위해 별도로 선언
	// 클래스에 @PropertySource 를 활용하여 저장 위치를 지정해주어야 한다.
	//		기본 경로는 /src/main/resources 임.
	@Value("${KINDERGARTEN_API_KEY}")
	private String kindergartenApiKey;
	@Value("${KAKAO_API_REST_KEY}")
	private String kakaoApiRestKey;
	
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

	@Override
	public String recommendCentersByAddress(ParamCenterAddress centerAddress) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		// 보육시설 기본 정보(Center 테이블 데이터) 가져오기
		SMapCenter keyword = new SMapCenter();
		keyword.setCenterState(centerAddress.getCenterState());
		keyword.setCenterCity(centerAddress.getCenterCity());
		List<SMapCenter> centerList = searchMapDao.getCenterListByStateCity(keyword);
		
		// 데이터 결합
		HashMap<Integer, SMapCenterKidsdataDetail> centerKidsdataDetailMap = new HashMap<Integer, SMapCenterKidsdataDetail>();
		
		// 기본 정보(Center table data) 결합
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
		
		// 어린이집 상세 정보 결합
		SMapKidsdataDetail keywordKidsdataDetail = new SMapKidsdataDetail();
		keywordKidsdataDetail.setCenterDetailState(centerAddress.getCenterState());
		keywordKidsdataDetail.setCenterDetailCity(centerAddress.getCenterCity());
		keywordKidsdataDetail.setCenterDetailLatitude(centerAddress.getCenterLatitude());
		keywordKidsdataDetail.setCenterDetailLongitude(centerAddress.getCenterLongitude());
		List<SMapKidsdataDetail> kidsdataDetailList = searchMapDao.getKidsdataDetailListByStateCityLatLng(keywordKidsdataDetail);
		
		// 어린이집 상세 정보를 기준으로, map에 집어넣기만 하는 코드
		for (SMapKidsdataDetail detail : kidsdataDetailList) {
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailState(detail.getCenterDetailState());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailCity(detail.getCenterDetailCity());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailBame(detail.getCenterDetailBame());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailClassification(detail.getCenterDetailClassification());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailCenteropen(detail.getCenterDetailCenteropen());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailOfficenumber(detail.getCenterDetailOfficenumber());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailAddress(detail.getCenterDetailAddress());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailPhone(detail.getCenterDetailPhone());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailFax(detail.getCenterDetailFax());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailRoomcount(detail.getCenterDetailRoomcount());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailRoomsize(detail.getCenterDetailRoomsize());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailPlaygroundcount(detail.getCenterDetailPlaygroundcount());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailTeachercount(detail.getCenterDetailTeachercount());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailRegularperson(detail.getCenterDetailRegularperson());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailCurrentperson(detail.getCenterDetailCurrentperson());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailLatitude(detail.getCenterDetailLatitude());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailLongitude(detail.getCenterDetailLongitude());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailVehicle(detail.getCenterDetailVehicle());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailHompage(detail.getCenterDetailHompage());
			centerKidsdataDetailMap.get(detail.getCenterNum()).setCenterDetailEstablish(detail.getCenterDetailEstablish());
		}
		
		// 조건 안 맞아서 상세 정보 없는 시설은 map에서 제거
		Iterator<Integer> iteratorKeyMap = centerKidsdataDetailMap.keySet().iterator();
		while (iteratorKeyMap.hasNext()) {
		    int key = iteratorKeyMap.next();
		    if (centerKidsdataDetailMap.get(key).getCenterCategory().equals("어린이집")
		    		&& centerKidsdataDetailMap.get(key).getCenterDetailState() == null) {
		    	iteratorKeyMap.remove();
		    }
		}
		
		
		
		// 유치원 데이터 결합하기
		// 유치원 API 호출해서 데이터 가져오기
		// 공시차수 입력 안 하면, 자동으로 최신 데이터를 가져옴

		// state, city 데이터로 시도코드 가져오기
		SMapSidocode sidocode = new SMapSidocode();
		sidocode.setSidoState(centerAddress.getCenterState());
		sidocode.setSidoCity(centerAddress.getCenterCity());
		sidocode = searchMapDao.getSidocodeOne(sidocode);
		
		// 유치원 API 호출 ----------------------------------
		// 일반 현황
		// GET 요청 보내기
		String url = "https://e-childschoolinfo.moe.go.kr/api/notice/basicInfo2.do?";
		String apiKey = "key=" + kindergartenApiKey;
		String parameter = "&sidoCode=" + sidocode.getSidoSidocode() + "&sggCode=" + sidocode.getSidoSggcode();
		String apiResponseBasicInfo2 = restTemplate.getForObject(url + apiKey + parameter, String.class);
		// JSON 문자열을 Java 객체로 변환
		KinderApiBasicInfo2Response kinderApiBasicInfo2Response = gson.fromJson(apiResponseBasicInfo2, KinderApiBasicInfo2Response.class);
		Set<KinderApiBasicInfo2> kinderApiBasicInfo2Set = kinderApiBasicInfo2Response.getKinderInfo();
		
		// 교실 면적 현황
		// GET 요청 보내기
		url = "https://e-childschoolinfo.moe.go.kr/api/notice/classArea.do?";
		String apiResponseClassArea = restTemplate.getForObject(url + apiKey + parameter, String.class);
		// JSON 문자열을 Java 객체로 변환
		KinderApiClassAreaResponse kinderApiClassAreaResponse = gson.fromJson(apiResponseClassArea, KinderApiClassAreaResponse.class);
		Set<KinderApiClassArea> kinderApiClassAreaSet = kinderApiClassAreaResponse.getKinderInfo();
		
		// 직위자격별교직원 현황
		// GET 요청 보내기
		url = "https://e-childschoolinfo.moe.go.kr/api/notice/teachersInfo.do?";
		String apiResponseTeachersInfo = restTemplate.getForObject(url + apiKey + parameter, String.class);
		// JSON 문자열을 Java 객체로 변환
		KinderApiTeachersInfoResponse kinderApiTeachersInfoResponse = gson.fromJson(apiResponseTeachersInfo, KinderApiTeachersInfoResponse.class);
		Set<KinderApiTeachersInfo> kinderApiTeachersInfoSet = kinderApiTeachersInfoResponse.getKinderInfo();
		
		// 통학차량 현황
		// GET 요청 보내기
		url = "https://e-childschoolinfo.moe.go.kr/api/notice/schoolBus.do?";
		String apiResponseSchoolBus = restTemplate.getForObject(url + apiKey + parameter, String.class);
		// JSON 문자열을 Java 객체로 변환
		KinderApiSchoolBusResponse kinderApiSchoolBusResponse = gson.fromJson(apiResponseSchoolBus, KinderApiSchoolBusResponse.class);
		Set<KinderApiSchoolBus> kinderApiSchoolBusSet = kinderApiSchoolBusResponse.getKinderInfo();
		
		// 데이터 결합
		Iterator<Map.Entry<Integer, SMapCenterKidsdataDetail>> iteratorEntrySetMap = centerKidsdataDetailMap.entrySet().iterator();
		while (iteratorEntrySetMap.hasNext()) {
		    Map.Entry<Integer, SMapCenterKidsdataDetail> entry = iteratorEntrySetMap.next();
		    int key = entry.getKey();
		    SMapCenterKidsdataDetail item = entry.getValue();
		    
		    if (item.getCenterCategory().equals("유치원")) {
				
				// 일반 현황
				Iterator<KinderApiBasicInfo2> iteratorBasicInfo2 = kinderApiBasicInfo2Set.iterator();
				while (iteratorBasicInfo2.hasNext()) {
				    KinderApiBasicInfo2 setItem = iteratorBasicInfo2.next();
				    if (setItem.getKindername().equals(item.getCenterName())) {
				        // 요소 제거
				    	iteratorBasicInfo2.remove();
				        
				        // 해당 요소를 사용하여 필요한 작업 수행
				        item.setCenterDetailBame(setItem.getKindername());
				        item.setCenterDetailClassification(setItem.getEstablish());
				        item.setCenterDetailAddress(setItem.getAddr());
				        item.setCenterDetailPhone(setItem.getTelno());
				        item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
				        int children = setItem.getAg3fpcnt() + setItem.getAg4fpcnt() +
				                setItem.getAg5fpcnt() + setItem.getMixfpcnt() + 
				                setItem.getSpcnfpcnt();
				        item.setCenterDetailCurrentperson(children);
				        item.setCenterDetailHompage(setItem.getHpaddr());
				        item.setCenterDetailEstablish(setItem.getEdate());
				        
				        break;
				    }
				}

				// 교실 면적 현황
				Iterator<KinderApiClassArea> iteratorClassArea = kinderApiClassAreaSet.iterator();
				while (iteratorClassArea.hasNext()) {
				    KinderApiClassArea setItem = iteratorClassArea.next();
				    if (setItem.getKindername().equals(item.getCenterName())) {
				        // 요소 제거
				    	iteratorClassArea.remove();
				        
				        // 해당 요소를 사용하여 필요한 작업 수행
				    	if (!setItem.getCrcnt().replaceAll("[^0-9]",  "").equals("")) {
							item.setCenterDetailRoomcount(Integer.parseInt(setItem.getCrcnt().replaceAll("[^0-9]",  "")));
				    	}
				    	if (!setItem.getClsrarea().replaceAll("[^0-9]",  "").equals("")) {
							item.setCenterDetailRoomsize(Integer.parseInt(setItem.getClsrarea().replaceAll("[^0-9]",  "")));
				    	}
				        
				        break;
				    }
				}

				// 직위자격별교직원 현황
				Iterator<KinderApiTeachersInfo> iteratorTeachersInfo = kinderApiTeachersInfoSet.iterator();
				while (iteratorTeachersInfo.hasNext()) {
				    KinderApiTeachersInfo setItem = iteratorTeachersInfo.next();
				    if (setItem.getKindername().equals(item.getCenterName())) {
				        // 요소 제거
				    	iteratorTeachersInfo.remove();
				        
				        // 해당 요소를 사용하여 필요한 작업 수행
						int teachers = setItem.getHdst_thcnt() + setItem.getAsps_thcnt() +
								setItem.getGnrl_thcnt() + setItem.getSpcn_thcnt() + 
								setItem.getNtcnt() + setItem.getNtrt_thcnt() + 
								setItem.getShcnt_thcnt() + setItem.getIncnt();
						item.setCenterDetailTeachercount(teachers);
				        
				        break;
				    }
				}

				// 통학차량 현황
				Iterator<KinderApiSchoolBus> iteratorSchoolBus = kinderApiSchoolBusSet.iterator();
				while (iteratorSchoolBus.hasNext()) {
				    KinderApiSchoolBus setItem = iteratorSchoolBus.next();
				    if (setItem.getKindername().equals(item.getCenterName())) {
				        // 요소 제거
				    	iteratorSchoolBus.remove();
				        
				        // 해당 요소를 사용하여 필요한 작업 수행
						item.setCenterDetailVehicle(setItem.getVhcl_oprn_yn());
				        
				        break;
				    }
				}

				// 위경도 데이터 가져와서 결합하기
				// GET 요청 URL
				String address = item.getCenterDetailAddress();
				String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

				// GET 요청 보내기
//				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "KakaoAK " + kakaoApiRestKey);
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<>(headers);
				
				String apiResponseKakaoApiAddress = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class).getBody();

				// JSON 문자열을 Java 객체로 변환
				KakaoApiAddressResponse kakaoApiAddressResponse = gson.fromJson(apiResponseKakaoApiAddress, KakaoApiAddressResponse.class);
				Set<KakaoApiAddressDocuments> kakaoApiAddressDocumentsSet = kakaoApiAddressResponse.getDocuments();
				
				// 위경도 데이터 결합하기
				double latGap = 0.004;
				double lngGap = latGap;
		        boolean isInBoundary = false;
				for (KakaoApiAddressDocuments setItem : kakaoApiAddressDocumentsSet) {
					// 영역 안에 있을 때만 값 정상적으로 저장
					if (setItem.getY() > centerAddress.getCenterLatitude() - latGap && setItem.getY() < centerAddress.getCenterLatitude() + latGap
							&& setItem.getX() > centerAddress.getCenterLongitude() - lngGap && setItem.getX() < centerAddress.getCenterLongitude() + lngGap) {
						item.setCenterDetailLatitude(setItem.getY());
						item.setCenterDetailLongitude(setItem.getX());
		                isInBoundary = true;
		                break;
					}
				}
		        if (!isInBoundary) {
		            // 영역 밖에 있으면 map에서 제거
		        	iteratorEntrySetMap.remove();
		        }
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
	public String searchCentersByCenterName(String centerName) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");


		// 보육시설 기본 정보(Center 테이블 데이터) 가져오기
		List<SMapCenter> centerList = searchMapDao.getCenterListByCenterName(centerName);
		
		// 데이터 결합
		HashMap<Integer, SMapCenterKidsdataDetail> centerKidsdataDetailMap = new HashMap<Integer, SMapCenterKidsdataDetail>();
		// 기본 정보(Center table data) 결합
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
		for (SMapCenterKidsdataDetail item : centerKidsdataDetailMap.values()) {
			if (item.getCenterCategory().equals("어린이집")) {
				// 어린이집 데이터 결합
				System.out.println("어린이집 상세데이터 저장");
				SMapKidsdataDetail temp = searchMapDao.getKidsdataDetailOne(item.getCenterNum());

				item.setCenterDetailState(temp.getCenterDetailState());
				item.setCenterDetailCity(temp.getCenterDetailCity());
				item.setCenterDetailBame(temp.getCenterDetailBame());
				item.setCenterDetailClassification(temp.getCenterDetailClassification());
				item.setCenterDetailCenteropen(temp.getCenterDetailCenteropen());
				item.setCenterDetailOfficenumber(temp.getCenterDetailOfficenumber());
				item.setCenterDetailAddress(temp.getCenterDetailAddress());
				item.setCenterDetailPhone(temp.getCenterDetailPhone());
				item.setCenterDetailFax(temp.getCenterDetailFax());
				item.setCenterDetailRoomcount(temp.getCenterDetailRoomcount());
				item.setCenterDetailRoomsize(temp.getCenterDetailRoomsize());
				item.setCenterDetailPlaygroundcount(temp.getCenterDetailPlaygroundcount());
				item.setCenterDetailTeachercount(temp.getCenterDetailTeachercount());
				item.setCenterDetailRegularperson(temp.getCenterDetailRegularperson());
				item.setCenterDetailCurrentperson(temp.getCenterDetailCurrentperson());
				item.setCenterDetailLatitude(temp.getCenterDetailLatitude());
				item.setCenterDetailLongitude(temp.getCenterDetailLongitude());
				item.setCenterDetailVehicle(temp.getCenterDetailVehicle());
				item.setCenterDetailHompage(temp.getCenterDetailHompage());
				item.setCenterDetailEstablish(temp.getCenterDetailEstablish());
			} else {
				// 유치원 데이터 결합
				System.out.println("유치원 상세데이터 저장");

				// state, city 데이터로 시도코드 가져오기
				SMapSidocode sidocode = new SMapSidocode();
				sidocode.setSidoState(item.getCenterState());
				sidocode.setSidoCity(item.getCenterCity());
				sidocode = searchMapDao.getSidocodeOne(sidocode);
				
				// 코드로 유치원 API 호출해서 데이터 가져오기
				// 공시차수 입력 안 하면, 자동으로 최신 데이터를 가져옴
				
				// 유치원 API - 일반 현황 ----------------------------------
		        // GET 요청 URL
				String url = "https://e-childschoolinfo.moe.go.kr/api/notice/basicInfo2.do?";
				String apiKey = "key=" + kindergartenApiKey;
				String parameter = "&sidoCode=" + sidocode.getSidoSidocode() + "&sggCode=" + sidocode.getSidoSggcode();

				// GET 요청 보내기
				String apiResponse = restTemplate.getForObject(url + apiKey + parameter, String.class);

				// JSON 문자열을 Java 객체로 변환
				KinderApiBasicInfo2Response kinderApiBasicInfo2Response = gson.fromJson(apiResponse, KinderApiBasicInfo2Response.class);
				Set<KinderApiBasicInfo2> kinderApiBasicInfo2Set = kinderApiBasicInfo2Response.getKinderInfo();
				
				// 가져온 데이터 중 적합한 데이터를 찾아서 데이터 결합하기
				for (KinderApiBasicInfo2 setItem : kinderApiBasicInfo2Set) {
					if (setItem.getKindername().equals(item.getCenterName())) {
//						item.setCenterDetailState(setItem.getCenterDetailState());
//						item.setCenterDetailCity(setItem.getCenterDetailCity());
						item.setCenterDetailBame(setItem.getKindername());
						item.setCenterDetailClassification(setItem.getEstablish());
//						item.setCenterDetailCenteropen(setItem.getCenterDetailCenteropen());
//						item.setCenterDetailOfficenumber(setItem.getCenterDetailOfficenumber());
						item.setCenterDetailAddress(setItem.getAddr());
						item.setCenterDetailPhone(setItem.getTelno());
//						item.setCenterDetailFax(setItem.getCenterDetailFax());
//						item.setCenterDetailRoomcount(setItem.getCenterDetailRoomcount());
//						item.setCenterDetailRoomsize(setItem.getCenterDetailRoomsize());
//						item.setCenterDetailPlaygroundcount(setItem.getCenterDetailPlaygroundcount());
//						item.setCenterDetailTeachercount(setItem.getCenterDetailTeachercount());
						item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
						int children = setItem.getAg3fpcnt() + setItem.getAg4fpcnt() +
								setItem.getAg5fpcnt() + setItem.getMixfpcnt() + 
								setItem.getSpcnfpcnt();
						item.setCenterDetailCurrentperson(children);
//						item.setCenterDetailLatitude(setItem.getCenterDetailLatitude());
//						item.setCenterDetailLongitude(setItem.getCenterDetailLongitude());
//						item.setCenterDetailVehicle(setItem.getCenterDetailVehicle());
						item.setCenterDetailHompage(setItem.getHpaddr());
						item.setCenterDetailEstablish(setItem.getEdate());
						break;
					}
				}

				// 유치원 API - 교실 면적 현황 ----------------------------------
		        // GET 요청 URL
				url = "https://e-childschoolinfo.moe.go.kr/api/notice/classArea.do?";
				apiKey = "key=" + kindergartenApiKey;
				parameter = "&sidoCode=" + sidocode.getSidoSidocode() + "&sggCode=" + sidocode.getSidoSggcode();

				// GET 요청 보내기
				apiResponse = restTemplate.getForObject(url + apiKey + parameter, String.class);
				
				// JSON 문자열을 Java 객체로 변환
				KinderApiClassAreaResponse kinderApiClassAreaResponse = gson.fromJson(apiResponse, KinderApiClassAreaResponse.class);
				Set<KinderApiClassArea> kinderApiClassAreaSet = kinderApiClassAreaResponse.getKinderInfo();
				
				// 가져온 데이터 중 적합한 데이터를 찾아서 데이터 결합하기
				for (KinderApiClassArea setItem : kinderApiClassAreaSet) {
					if (setItem.getKindername().equals(item.getCenterName())) {
//						item.setCenterDetailState(setItem.getCenterDetailState());
//						item.setCenterDetailCity(setItem.getCenterDetailCity());
//						item.setCenterDetailBame(setItem.getKindername());
//						item.setCenterDetailClassification(setItem.getEstablish());
//						item.setCenterDetailCenteropen(setItem.getCenterDetailCenteropen());
//						item.setCenterDetailOfficenumber(setItem.getCenterDetailOfficenumber());
//						item.setCenterDetailAddress(setItem.getAddr());
//						item.setCenterDetailPhone(setItem.getTelno());
//						item.setCenterDetailFax(setItem.getCenterDetailFax());
						item.setCenterDetailRoomcount(Integer.parseInt(setItem.getCrcnt().replaceAll("[^0-9]",  "")));
						item.setCenterDetailRoomsize(Integer.parseInt(setItem.getClsrarea().replaceAll("[^0-9]",  "")));
//						item.setCenterDetailPlaygroundcount(setItem.getCenterDetailPlaygroundcount());
//						item.setCenterDetailTeachercount(setItem.getCenterDetailTeachercount());
//						item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
//						item.setCenterDetailCurrentperson(setItem.getCenterDetailCurrentperson());
//						item.setCenterDetailLatitude(setItem.getCenterDetailLatitude());
//						item.setCenterDetailLongitude(setItem.getCenterDetailLongitude());
//						item.setCenterDetailVehicle(setItem.getCenterDetailVehicle());
//						item.setCenterDetailHompage(setItem.getHpaddr());
//						item.setCenterDetailEstablish(setItem.getEdate());
						break;
					}
				}

				// 유치원 API - 직위자격별교직원 현황 ----------------------------------
		        // GET 요청 URL
				url = "https://e-childschoolinfo.moe.go.kr/api/notice/teachersInfo.do?";
				apiKey = "key=" + kindergartenApiKey;
				parameter = "&sidoCode=" + sidocode.getSidoSidocode() + "&sggCode=" + sidocode.getSidoSggcode();

				// GET 요청 보내기
				apiResponse = restTemplate.getForObject(url + apiKey + parameter, String.class);
				
				// JSON 문자열을 Java 객체로 변환
				KinderApiTeachersInfoResponse kinderApiTeachersInfoResponse = gson.fromJson(apiResponse, KinderApiTeachersInfoResponse.class);
				Set<KinderApiTeachersInfo> kinderApiTeachersInfoSet = kinderApiTeachersInfoResponse.getKinderInfo();
				
				// 가져온 데이터 중 적합한 데이터를 찾아서 데이터 결합하기
				for (KinderApiTeachersInfo setItem : kinderApiTeachersInfoSet) {
					if (setItem.getKindername().equals(item.getCenterName())) {
//						item.setCenterDetailState(setItem.getCenterDetailState());
//						item.setCenterDetailCity(setItem.getCenterDetailCity());
//						item.setCenterDetailBame(setItem.getKindername());
//						item.setCenterDetailClassification(setItem.getEstablish());
//						item.setCenterDetailCenteropen(setItem.getCenterDetailCenteropen());
//						item.setCenterDetailOfficenumber(setItem.getCenterDetailOfficenumber());
//						item.setCenterDetailAddress(setItem.getAddr());
//						item.setCenterDetailPhone(setItem.getTelno());
//						item.setCenterDetailFax(setItem.getCenterDetailFax());
//						item.setCenterDetailRoomcount(Integer.parseInt(setItem.getCrcnt().replaceAll("[^0-9]",  "")));
//						item.setCenterDetailRoomsize(Integer.parseInt(setItem.getClsrarea().replaceAll("[^0-9]",  "")));
//						item.setCenterDetailPlaygroundcount(setItem.getCenterDetailPlaygroundcount());
						int teachers = setItem.getHdst_thcnt() + setItem.getAsps_thcnt() +
								setItem.getGnrl_thcnt() + setItem.getSpcn_thcnt() + 
								setItem.getNtcnt() + setItem.getNtrt_thcnt() + 
								setItem.getShcnt_thcnt() + setItem.getIncnt();
						item.setCenterDetailTeachercount(teachers);
//						item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
//						item.setCenterDetailCurrentperson(setItem.getCenterDetailCurrentperson());
//						item.setCenterDetailLatitude(setItem.getCenterDetailLatitude());
//						item.setCenterDetailLongitude(setItem.getCenterDetailLongitude());
//						item.setCenterDetailVehicle(setItem.getCenterDetailVehicle());
//						item.setCenterDetailHompage(setItem.getHpaddr());
//						item.setCenterDetailEstablish(setItem.getEdate());
						break;
					}
				}

				// 유치원 API - 통학차량 현황 ----------------------------------
		        // GET 요청 URL
				url = "https://e-childschoolinfo.moe.go.kr/api/notice/schoolBus.do?";
				apiKey = "key=" + kindergartenApiKey;
				parameter = "&sidoCode=" + sidocode.getSidoSidocode() + "&sggCode=" + sidocode.getSidoSggcode();

				// GET 요청 보내기
				apiResponse = restTemplate.getForObject(url + apiKey + parameter, String.class);
				
				// JSON 문자열을 Java 객체로 변환
				KinderApiSchoolBusResponse kinderApiSchoolBusResponse = gson.fromJson(apiResponse, KinderApiSchoolBusResponse.class);
				Set<KinderApiSchoolBus> kinderApiSchoolBusSet = kinderApiSchoolBusResponse.getKinderInfo();
				
				// 가져온 데이터 중 적합한 데이터를 찾아서 데이터 결합하기
				for (KinderApiSchoolBus setItem : kinderApiSchoolBusSet) {
					if (setItem.getKindername().equals(item.getCenterName())) {
//						item.setCenterDetailState(setItem.getCenterDetailState());
//						item.setCenterDetailCity(setItem.getCenterDetailCity());
//						item.setCenterDetailBame(setItem.getKindername());
//						item.setCenterDetailClassification(setItem.getEstablish());
//						item.setCenterDetailCenteropen(setItem.getCenterDetailCenteropen());
//						item.setCenterDetailOfficenumber(setItem.getCenterDetailOfficenumber());
//						item.setCenterDetailAddress(setItem.getAddr());
//						item.setCenterDetailPhone(setItem.getTelno());
//						item.setCenterDetailFax(setItem.getCenterDetailFax());
//						item.setCenterDetailRoomcount(Integer.parseInt(setItem.getCrcnt().replaceAll("[^0-9]",  "")));
//						item.setCenterDetailRoomsize(Integer.parseInt(setItem.getClsrarea().replaceAll("[^0-9]",  "")));
//						item.setCenterDetailPlaygroundcount(setItem.getCenterDetailPlaygroundcount());
//						item.setCenterDetailTeachercount(setItem.getCenterDetailTeachercount());
//						item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
//						item.setCenterDetailCurrentperson(setItem.getCenterDetailCurrentperson());
//						item.setCenterDetailLatitude(setItem.getCenterDetailLatitude());
//						item.setCenterDetailLongitude(setItem.getCenterDetailLongitude());
						item.setCenterDetailVehicle(setItem.getVhcl_oprn_yn());
//						item.setCenterDetailHompage(setItem.getHpaddr());
//						item.setCenterDetailEstablish(setItem.getEdate());
						break;
					}
				}

//				// 유치원 API - 안전점검교육실시현황 현황 ----------------------------------
//		        // GET 요청 URL
//				url = "https://e-childschoolinfo.moe.go.kr/api/notice/safetyEdu.do?";
//				apiKey = "key=" + kindergartenApiKey;
//				parameter = "&sidoCode=" + sidocode.getSidoSidocode() + "&sggCode=" + sidocode.getSidoSggcode();
//
//				// GET 요청 보내기
//				apiResponse = restTemplate.getForObject(url + apiKey + parameter, String.class);
//				
//				// JSON 문자열을 Java 객체로 변환
//				KinderApiSafetyEduResponse kinderApiSafetyEduResponse = gson.fromJson(apiResponse, KinderApiSafetyEduResponse.class);
//				Set<KinderApiSafetyEdu> kinderApiSafetyEduSet = kinderApiSafetyEduResponse.getKinderInfo();
//				
//				// 가져온 데이터 중 적합한 데이터를 찾아서 데이터 결합하기
//				for (KinderApiSafetyEdu setItem : kinderApiSafetyEduSet) {
//					if (setItem.getKindername().equals(item.getCenterName())) {
////						item.setCenterDetailState(setItem.getCenterDetailState());
////						item.setCenterDetailCity(setItem.getCenterDetailCity());
////						item.setCenterDetailBame(setItem.getKindername());
////						item.setCenterDetailClassification(setItem.getEstablish());
////						item.setCenterDetailCenteropen(setItem.getCenterDetailCenteropen());
////						item.setCenterDetailOfficenumber(setItem.getCenterDetailOfficenumber());
////						item.setCenterDetailAddress(setItem.getAddr());
////						item.setCenterDetailPhone(setItem.getTelno());
////						item.setCenterDetailFax(setItem.getCenterDetailFax());
////						item.setCenterDetailRoomcount(Integer.parseInt(setItem.getCrcnt().replaceAll("[^0-9]",  "")));
////						item.setCenterDetailRoomsize(Integer.parseInt(setItem.getClsrarea().replaceAll("[^0-9]",  "")));
////						item.setCenterDetailPlaygroundcount(setItem.getCenterDetailPlaygroundcount());
////						item.setCenterDetailTeachercount(setItem.getCenterDetailTeachercount());
////						item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
////						item.setCenterDetailCurrentperson(setItem.getCenterDetailCurrentperson());
////						item.setCenterDetailLatitude(setItem.getCenterDetailLatitude());
////						item.setCenterDetailLongitude(setItem.getCenterDetailLongitude());
////						item.setCenterDetailVehicle(setItem.getVhcl_oprn_yn());
////						item.setCenterDetailHompage(setItem.getHpaddr());
////						item.setCenterDetailEstablish(setItem.getEdate());
//						break;
//					}
//				}

				// 카카오 API - 위경도 데이터 가져오기 ----------------------------------
				// GET 요청 URL
				String address = item.getCenterDetailAddress();
				String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

				// GET 요청 보내기
//				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "KakaoAK " + kakaoApiRestKey);
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<>(headers);
				
				apiResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class).getBody();

				// JSON 문자열을 Java 객체로 변환
				KakaoApiAddressResponse kakaoApiAddressResponse = gson.fromJson(apiResponse, KakaoApiAddressResponse.class);
				Set<KakaoApiAddressDocuments> kakaoApiAddressDocumentsSet = kakaoApiAddressResponse.getDocuments();
				
				// 위경도 데이터 결합하기
				for (KakaoApiAddressDocuments setItem : kakaoApiAddressDocumentsSet) {
					if (true) {
	//					item.setCenterDetailState(setItem.getCenterDetailState());
	//					item.setCenterDetailCity(setItem.getCenterDetailCity());
	//					item.setCenterDetailBame(setItem.getKindername());
	//					item.setCenterDetailClassification(setItem.getEstablish());
	//					item.setCenterDetailCenteropen(setItem.getCenterDetailCenteropen());
	//					item.setCenterDetailOfficenumber(setItem.getCenterDetailOfficenumber());
	//					item.setCenterDetailAddress(setItem.getAddr());
	//					item.setCenterDetailPhone(setItem.getTelno());
	//					item.setCenterDetailFax(setItem.getCenterDetailFax());
	//					item.setCenterDetailRoomcount(Integer.parseInt(setItem.getCrcnt().replaceAll("[^0-9]",  "")));
	//					item.setCenterDetailRoomsize(Integer.parseInt(setItem.getClsrarea().replaceAll("[^0-9]",  "")));
	//					item.setCenterDetailPlaygroundcount(setItem.getCenterDetailPlaygroundcount());
	//					item.setCenterDetailTeachercount(setItem.getCenterDetailTeachercount());
	//					item.setCenterDetailRegularperson(setItem.getPrmstfcnt());
	//					item.setCenterDetailCurrentperson(setItem.getCenterDetailCurrentperson());
						item.setCenterDetailLatitude(setItem.getY());
						item.setCenterDetailLongitude(setItem.getX());
	//					item.setCenterDetailVehicle(setItem.getVhcl_oprn_yn());
	//					item.setCenterDetailHompage(setItem.getHpaddr());
	//					item.setCenterDetailEstablish(setItem.getEdate());
					}
				}

			}
		}
		
		// 객체가 아니라 배열로 return 해주기 위해 map을 list로 변경
		List<SMapCenterKidsdataDetail> centerKidsdataDetailList = new ArrayList<SMapCenterKidsdataDetail>(centerKidsdataDetailMap.values());

		// json 데이터로 변경
//		Gson gson = new Gson();
		String json = gson.toJson(centerKidsdataDetailList);
		log.debug(json);
			// log(map) 의 출력
			// {"6":{"centerNum":6,"centerName":"SGI서울보증 어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SGI서울보증 어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3128,"centerDetailAddress":"서울특별시 종로구 김상옥로 29 2층","centerDetailPhone":"02-3671-7051","centerDetailFax":"02-3671-7053","centerDetailRoomcount":4,"centerDetailRoomsize":178,"centerDetailPlaygroundcount":"0","centerDetailTeachercount":13,"centerDetailRegularperson":49,"centerDetailCurrentperson":25,"centerDetailLatitude":37.573628643388055,"centerDetailLongitude":127.00103012788806,"centerDetailVehicle":"미운영","centerDetailHompage":"https://www.puruni.com/sgic","centerDetailEstablish":"2016-02-22"},"7":{"centerNum":7,"centerName":"SK ecoplant 행복날개어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SK ecoplant 행복날개어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3143,"centerDetailAddress":"서울특별시 종로구 율곡로2길 19 SK ecoplant 행복날개어린이집","centerDetailPhone":"02-6395-0202","centerDetailFax":"02-6395-0203","centerDetailRoomcount":4,"centerDetailRoomsize":177,"centerDetailPlaygroundcount":"1","centerDetailTeachercount":15,"centerDetailRegularperson":49,"centerDetailCurrentperson":30,"centerDetailLatitude":37.574433532681724,"centerDetailLongitude":126.98114619280257,"centerDetailVehicle":"미운영","centerDetailHompage":"www.puruni.com/skec","centerDetailEstablish":"2013-08-27"},"8":{"centerNum":8,"centerName":"SK네트웍스새싹어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SK네트웍스새싹어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3190,"centerDetailAddress":"서울특별시 종로구 청계천로 85 삼일빌딩, 3층","centerDetailPhone":"070-7800-2115","centerDetailFax":"070-7800-2118","centerDetailRoomcount":4,"centerDetailRoomsize":208,"centerDetailPlaygroundcount":"0","centerDetailTeachercount":9,"centerDetailRegularperson":49,"centerDetailCurrentperson":9,"centerDetailLatitude":37.56505624,"centerDetailLongitude":126.9830142,"centerDetailVehicle":"미운영","centerDetailHompage":"http://moamom016.kiznet.co.kr","centerDetailEstablish":"2009-01-29"},"9":{"centerNum":9,"centerName":"SK행복어린이집","centerCategory":"어린이집","centerState":"서울특별시","centerCity":"종로구","centerType":"직장일반","centerExtendcare":1,"centerDetailState":"서울특별시","centerDetailCity":"종로구","centerDetailBame":"SK행복어린이집","centerDetailClassification":"직장","centerDetailCenteropen":"정상","centerDetailOfficenumber":3188,"centerDetailAddress":"서울특별시 종로구 종로 26 SK서린동빌딩 2층","centerDetailPhone":"02-2121-1611","centerDetailFax":"02-2121-1610","centerDetailRoomcount":7,"centerDetailRoomsize":364,"centerDetailPlaygroundcount":"1","centerDetailTeachercount":30,"centerDetailRegularperson":99,"centerDetailCurrentperson":73,"centerDetailLatitude":37.56967307,"centerDetailLongitude":126.980289,"centerDetailVehicle":"미운영","centerDetailHompage":"www.puruni.com/skseoul","centerDetailEstablish":"2007-09-14"}} == 2023-05-09 11:26:10,203
			// log(list) 의 출력
			// [{"centerNum":6,"centerName":"SGI서울보증 어린이집","centerCategory":"어린이집","centerStat
		return json;
	}

	// 유치원 API 가져오기 테스트용 method
	public String getKinderDataByApi(String centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
        // RestTemplate 인스턴스 생성
//        RestTemplate restTemplate = new RestTemplate();

        // GET 요청 URL
		String url = "https://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do?";
		String apiKey = "key=" + kindergartenApiKey;
		String parameter = "&sidoCode=27&sggCode=27140";

		// GET 요청 보내기
		String jsonString = restTemplate.getForObject(url + apiKey + parameter, String.class);
		log.debug(jsonString);
		
		return jsonString;
	}

	// Kakao API 주소데이터 테스트용 method
	public String getPositionByKakaoApi(String centerNum) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.debug("@@@ " + methodName + " 실행");
		
		// 카카오 API - 위경도 데이터 가져오기 ----------------------------------
		// GET 요청 URL
		String address = "경기도 성남시 수정구 남문로60번길 9-1";
		String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

		// GET 요청 보내기
//		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + kakaoApiRestKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		// response를 json으로 변환
		String jsonResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class).getBody();

		// JSON 문자열을 Java 객체로 변환
		KakaoApiAddressResponse kakaoApiAddressResponse = gson.fromJson(jsonResponse, KakaoApiAddressResponse.class);
		Set<KakaoApiAddressDocuments> kakaoApiAddressDocumentsSet = kakaoApiAddressResponse.getDocuments();
		
		// 위경도 데이터 추출하기
		for (KakaoApiAddressDocuments setItem : kakaoApiAddressDocumentsSet) {
			log.debug("Longitude(x) : " + setItem.getX() + ", " + "Latitude(y) : " + setItem.getY());
		}
		
		return jsonResponse;
	}
	
}
