package com.mkyong.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mkyong.dao.UserEntity;

public class MyUserDetails extends User implements UserDetails{
	
		
	    protected MyUserDetails(UserEntity user) {
	 
	    	super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN","USER"));
	    }
	    
	    
	    	 
	    	    @Override
	    	    public boolean isAccountNonExpired() {
	    	        return true;
	    	    }
	    	 
	    	    @Override
	    	    public boolean isAccountNonLocked() {
	    	        return true;
	    	    }
	    	 
	    	    @Override
	    	    public boolean isCredentialsNonExpired() {
	    	        return true;
	    	    }
	    	 
	    	    @Override
	    	    public boolean isEnabled() {
	    	        return true;
	    	    }
	    	
	    	
	    
}
