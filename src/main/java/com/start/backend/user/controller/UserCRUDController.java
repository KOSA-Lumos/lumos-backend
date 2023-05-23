package com.start.backend.user.controller;

import java.lang.annotation.Retention;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.start.backend.user.vo.User;
import com.start.backend.user.vo.UserSearch;
import com.start.backend.user.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping(produces = "application/json; charset=utf-8", value="/user")
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
		user.setUserId(userId); // setter라는 메소드를 이용
		 user.getUserId();
		log.debug(user);
		user = userService.getKakaoUser(user);
		log.debug(user);
//		System.out.println("----code---- : " + userId);

//		String access_Token = userService.getAccessToken(userId);
//		System.out.println("----access_Token---- : " + access_Token);

		return user;
	}
	
	@PostMapping(value="/signup")
	  public int signUp(@RequestBody User user) {

		log.debug("singup cont");
		log.debug(user);
		int signUpUser = userService.signUpUser(user);
		log.debug("~~뭐가 들었나~~" + signUpUser);
		
	    return signUpUser;
	  }

	@GetMapping(value="/login")
	public User login(@ModelAttribute User user) {
		log.debug(user);
		User loginUser = userService.loginUser(user);
		log.debug("test" + loginUser);
		return loginUser;
	}
	
	
//	@PostMapping(value="/check-userid")
//	public int checkUserId(String userId) {
//		int checkUserId = userService.checkUserId(userId);
//		log.debug(checkUserId);
//		return checkUserId;
//	}
	
	@GetMapping(value="/check-userid")
	public Map<String, Boolean> checkUserId(@RequestParam String userId) {
		boolean isExist = userService.checkUserId(userId) > 0;
		Map<String, Boolean> response = new HashMap<>();
		response.put("isExist", isExist);
		log.debug(isExist);
		return response;
	}
	
// ---------------------------------- 관리자 ----------------------------------------
	@GetMapping(value = "/all-user")
	public List<User> getUsers(@ModelAttribute User user) {

		List<User> userList = userService.getUsers(user);
		log.debug(userList);
		return userList;
	}
	
	@Override
	@PutMapping(value="/set-user/{userId}")
	public int deleteUser(@PathVariable String userId) throws Exception {
		
		int deleteUser = userService.deleteUser(userId);
		
		return deleteUser;
		
	}
	
	
	@GetMapping(value="/user-search")
	  public List<User> getUserSearch(String keyword) {

		log.debug("-------- userSerch cont");
		log.debug(keyword);
		List<User> getUserSearch = userService.getUserSearch(keyword);
		log.debug("~~뭐가 들었나~~" + getUserSearch);
		
	    return getUserSearch;
	  }
	
	
//	@GetMapping(value="/login")
//	public ResponseEntity<String>  login(@ModelAttribute User user) {
//		
//		User loginUser = userService.loginUser(user);
//		log.debug(loginUser);
//		
//		
//			
//		return ResponseEntity.ok().body("token");
//	}
	
	
	
//	@PostMapping(value="/logout")
//	public 


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

//	@Override
//	@PutMapping(value="/user/{userId}")
//	public void updateUser(@PathVariable String userId) throws Exception {
//		
//		userService.updateUser(userId);
//		
//	}

//	@Override
//	@DeleteMapping(value="/user/{userId}")
//	public void deleteUser(@PathVariable String userId) throws Exception {
//		
//		userService.deleteUser(userId);
//	}

}
