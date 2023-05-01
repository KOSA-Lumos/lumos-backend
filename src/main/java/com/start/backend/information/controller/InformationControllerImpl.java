package com.start.backend.information.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.information.service.InformationService;
import com.start.backend.information.vo.Information;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class InformationControllerImpl implements InformationController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private InformationService informationService;

	public InformationControllerImpl(InformationService informationService) {
		this.informationService = informationService;
	}

	@Override
	@GetMapping(value="/kindergartendetail/{center_num}/information")
	public Information getInformation(@PathVariable String center_num) {
		
		log.debug("ssdfsdf");
		Information information = informationService.getInformation(center_num);
		
		return information;
	}

	@Override
//	@GetMapping(value="/kindergartendetail/{center_num}/information")
	public Information get() {
	    Information information = informationService.getInformation("123");
	    return information;
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
