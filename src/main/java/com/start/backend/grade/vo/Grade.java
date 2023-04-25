package com.start.backend.grade.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

	private int childcare_eval_num;
	private String childcare_eval_grade;
	private String childcare_eval_communication;
	private String childcare_eval_environment;
	private String childcare_eval_safety;
	private String childcare_eval_teacher;
	private String childcare_eval_date;
}