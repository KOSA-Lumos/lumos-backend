package com.start.backend.grade.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.grade.service.GradeService;
import com.start.backend.grade.vo.Grade;
import com.start.backend.information.vo.Information;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class GradeControllerImpl implements GradeController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private GradeService gradeService;

	public GradeControllerImpl(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@Override
	@GetMapping(value="/kindergartendetail/{center_num}/grade")
	public Grade getGrade(@PathVariable String center_num) {
		
		Grade grade = gradeService.getGrade(center_num);
		
		return grade;
	}

	@Override
	public void updateUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Information addUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Information get() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	@PostMapping(value="/user")
//	public Sample addUser(@PathVariable String userId) {
//		
//		Sample user = userService.addUser(userId);
//		
//		return user;
//	}
//
//	@Override
//	@PutMapping(value="/user/{userId}")
//	public void updateUser(@PathVariable String userId) throws Exception {
//		
//		userService.updateUser(userId);
//		
//	}
//
//	@Override
//	@DeleteMapping(value="/user/{userId}")
//	public void deleteUser(@PathVariable String userId) throws Exception {
//		
//		userService.deleteUser(userId);
//	}
}
