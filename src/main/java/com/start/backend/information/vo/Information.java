package com.start.backend.information.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {

	private int center_num;
	private String center_name;
	private String center_category;
	private String center_state;
	private String center_city;
	private String center_type;
	private Boolean center_extendcare;
}