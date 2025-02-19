package com.app.Sevices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.Entity.User;
import com.app.Repository.UserRespository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerUserService implements UserDetailsService{
	
	@Autowired
	private UserRespository userRespository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRespository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("user Not Found With given username :"+username);
		}
		List<GrantedAuthority> authorities=new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

}
