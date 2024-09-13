package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.User;
import com.app.Exception.UserException;
import com.app.Sevices.UserService;
import com.app.request.UpdateUserResuest;
import com.app.respose.ApiResponse;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User>getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException{
		User user=userService.findUserProfile(token);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{query}")
	public ResponseEntity<List<User>> serachUserHandler(@PathVariable("query") String query){
		List<User>users=userService.SerachUser(query);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);	
	}
	
	
	public ResponseEntity<ApiResponse>updateUserHandler(@RequestBody UpdateUserResuest req, @RequestHeader("Authorization") String token) throws UserException{
		User user=userService.findUserProfile(token);
		userService.updateUser(user.getId(), req);
		ApiResponse res=new ApiResponse("User Updated Succesfully",true);
		return new ResponseEntity<>(res ,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
}
