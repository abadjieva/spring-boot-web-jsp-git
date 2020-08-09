package com.mkyong.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mkyong.User;
import com.mkyong.UserList;
import com.mkyong.dao.Status;
import com.mkyong.dao.StatusEnum;
import com.mkyong.dao.UserEntity;


public interface UserService {
	
	//@PreAuthorize("hasRole('ADMIN')")
	List<User> findAllUsers(); 
	
	User findByName(String name);
	
	void setzenStatusStop(Status status) throws Exception;
	
	public UserEntity getUserByName(String username);
	
	public String getAllUserFromDAO()  throws Exception;
	

	

	
}
