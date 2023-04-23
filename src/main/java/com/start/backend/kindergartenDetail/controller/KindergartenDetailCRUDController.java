package com.start.backend.kindergartenDetail.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.transactionSample.service.UserService;
import com.start.backend.transactionSample.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class KindergartenDetailCRUDController implements KindergartenDetailController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private UserService userService;

	public KindergartenDetailCRUDController(UserService userService) {
		this.userService = userService;
	}

	@Override
	@GetMapping(value="/user/{userId}")
	public User getUser(@PathVariable String userId) {
		
		User user = userService.getUser(userId);
		
		return user;
	}
	
	@Override
	@PostMapping(value="/user")
	public User addUser(@PathVariable String userId) {
		
		User user = userService.addUser(userId);
		
		return user;
	}

	@Override
	@PutMapping(value="/user/{userId}")
	public void updateUser(@PathVariable String userId) throws Exception {
		
		userService.updateUser(userId);
		
	}

	@Override
	@DeleteMapping(value="/user/{userId}")
	public void deleteUser(@PathVariable String userId) throws Exception {
		
		userService.deleteUser(userId);
	}
}
