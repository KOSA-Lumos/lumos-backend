package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiTeachersInfo {
	
	private String officeedu;
	private String subofficeedu;
	private String kinderCode;
	private String kindername;
	private String establish;
	private int drcnt;
	private int adcnt;
	private int hdst_thcnt;
	private int asps_thcnt;
	private int gnrl_thcnt;
	private int spcn_thcnt;
	private int ntcnt;
	private int ntrt_thcnt;
	private int shcnt_thcnt;
	private int incnt;
	private int owcnt;
	private int hdst_tchr_qacnt;
	private int rgth_gd1_qacnt;
	private int rgth_gd2_qacnt;
	private int asth_qacnt;
	private int pbnttmng;
}
