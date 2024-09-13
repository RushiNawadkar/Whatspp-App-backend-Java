package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Config.TokenProvider;
import com.app.Entity.User;
import com.app.Exception.UserException;
import com.app.Repository.UserRespository;
import com.app.Sevices.CustomerUserService;
import com.app.request.LoginRequest;
import com.app.respose.ApiResponse;
import com.app.respose.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private CustomerUserService customerUserService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		String email=user.getEmail();
		String fullname=user.getFull_Name();
		String password=user.getPassword();
		
		User isuser=userRespository.findByEmail(email);
		if(isuser!=null) {
			throw new UserException("Email is used with another account" +email);
		}
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setFull_Name(fullname);
		createdUser.setPassword(passwordEncoder.encode(password));
		
		userRespository.save(createdUser);
		
	   Authentication authentication=new UsernamePasswordAuthenticationToken(email, password);
	   SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	   String jwt= tokenProvider.generateToken(authentication);
	   
	   AuthResponse authResponse=new AuthResponse(jwt,true);
	   
	   
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.ACCEPTED);
		
	}

	public ResponseEntity<AuthResponse> LoginHandler(@RequestBody LoginRequest req){
		 
		String email=req.getEmail();
		String password=req.getPassword();
		
		Authentication authentcation=authenticate(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentcation);
		
		String jwt=tokenProvider.generateToken(authentcation);
		
		AuthResponse res=new AuthResponse(jwt,true);
		return new ResponseEntity<AuthResponse>(res,HttpStatus.ACCEPTED);
		
	}
	
	public Authentication authenticate(String username,String password) {
		UserDetails userDetails=customerUserService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username And Password");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password and Username");
		}
		
		return new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
	}
}
