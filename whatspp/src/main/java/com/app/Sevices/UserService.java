package com.app.Sevices;

import java.util.List;

import com.app.Entity.User;
import com.app.Exception.UserException;
import com.app.request.UpdateUserResuest;

public interface UserService {

	public User findUserbyId(Long id) throws UserException;
	
	public User findUserProfile(String jwt) throws UserException;
	
	public User updateUser(Long id,  UpdateUserResuest req) throws UserException;
	
	public List<User>SerachUser(String query);
	
	
}
