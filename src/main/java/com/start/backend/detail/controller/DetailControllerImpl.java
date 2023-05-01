package com.start.backend.detail.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.detail.service.DetailService;
import com.start.backend.detail.vo.Detail;
import com.start.backend.information.vo.Information;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class DetailControllerImpl implements DetailController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private DetailService detailService;

	public DetailControllerImpl(DetailService detailService) {
		this.detailService = detailService;
	}

	@Override
	@GetMapping(value="/kindergartendetail/{center_num}/detail")
	public Detail getDetail(@PathVariable String center_num) {
		
		Detail detail = detailService.getDetail(center_num);
		
		return detail;
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
//	public User addUser(@PathVariable String userId) {
//		
//		User user = userService.addUser(userId);
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
