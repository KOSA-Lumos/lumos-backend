package com.start.backend.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.start.backend.user.vo.User;
import com.start.backend.user.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:8079", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8", value="/user")
//@RequestMapping(value="/")
public class UserCRUDController implements UserController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private UserService userService;

	public UserCRUDController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public void test(){
		log.debug("테스트");
	}

	@Override
	@GetMapping(value="/kakaoLogin")
	public User getkakaoLogin(@RequestParam("userId") String userId) {
		log.debug("dddddd");
		User user = new User();
		log.debug(user);
		user.setUserId(userId);
		log.debug(user);
		user = userService.getKakaoUser(user);
		log.debug(user);
//		System.out.println("----code---- : " + userId);

//		String access_Token = userService.getAccessToken(userId);
//		System.out.println("----access_Token---- : " + access_Token);

		return user;
	}
	
	@PostMapping("/signup")
	  public int signUp(User user) {

		int signUpUser = userService.signUpUser(user);
		log.debug(signUpUser);
		
	    return signUpUser;
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
