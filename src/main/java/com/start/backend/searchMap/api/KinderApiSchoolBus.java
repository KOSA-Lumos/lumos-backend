package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiSchoolBus {
	
	private String officeedu;
	private String subofficeedu;
	private String kinderCode;
	private String kindername;
	private String establish;
	private String vhcl_oprn_yn;
	private int opra_vhcnt;
	private int dclr_vhcnt;
	private int psg9_dclr_vhcnt;
	private int psg12_dclr_vhcnt;
	private int psg15_dclr_vhcnt;
	private int pbntTmng;
	
}
