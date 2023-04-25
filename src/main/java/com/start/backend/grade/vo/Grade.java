package com.start.backend.grade.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

	private int center_num;
	private String center_name;
	private String center_state;
	private String center_city;
	private int childcare_eval_regionnum;
	private String childcare_eval_grade;
	private String childcare_eval_communication;
	private String childcare_eval_environment;
	private String childcare_eval_safety;
	private String childcare_eval_teacher;
	private String childcare_eval_date;
}