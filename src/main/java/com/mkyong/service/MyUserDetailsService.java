package com.mkyong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mkyong.dao.UserEntity;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userservice;
	@Autowired
	private PasswordEncoder passwordEncoder;


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("*************************** loadUserByUsername "+username);
		UserEntity user=userservice.getUserByName(username);
	
		// wenn in der DB nicht encodet gespeichert
		//String passw=passwordEncoder.encode(user.getPassword());
		//user.setPassword(passw);
		    	
		    
		
		if (user == null) {
            throw new UsernameNotFoundException(username);
        }
		System.out.println(user.getUsername()+"  "+user.getPassword());
        return new MyUserDetails(user);

		
	}
}
