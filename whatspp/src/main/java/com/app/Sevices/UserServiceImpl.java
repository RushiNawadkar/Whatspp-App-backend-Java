package com.app.Sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.app.Config.TokenProvider;
import com.app.Entity.User;
import com.app.Exception.UserException;
import com.app.Repository.UserRespository;
import com.app.request.UpdateUserResuest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private TokenProvider tokenProvider;
	

	@Override
	public User findUserbyId(Long id) throws UserException {
		Optional<User>opt=userRespository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("User Not Found With id"+id);
	}

	@Override
	public User findUserProfile(String jwt) throws UserException {
		String email=tokenProvider.getEmailFromtoken(jwt);
		if(email ==null) {
			throw new BadCredentialsException("recived invalid token---");
		}
		User user= userRespository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("User Not Found with email"+email);
		}
		return user;
	}

	@Override
	public User updateUser(Long id, UpdateUserResuest req) throws UserException {
		User user=findUserbyId(id);
		if(req.getFull_Name()!=null) {
			user.setFull_Name(req.getFull_Name());
		}
		if(req.getProfile_picture()!=null) {
			user.setProfile_picture(req.getProfile_picture());
		}
		return userRespository.save(user);
	}

	@Override
	public List<User> SerachUser(String query) {
		List<User> users=userRespository.SearchUser(query);
		return users;
	}

	
}
