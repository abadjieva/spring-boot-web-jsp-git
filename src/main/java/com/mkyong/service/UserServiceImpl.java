package com.mkyong.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.User;
import com.mkyong.UserList;
import com.mkyong.dao.PartnerfirmaDAO;
import com.mkyong.dao.Status;
import com.mkyong.dao.StatusEnum;
import com.mkyong.dao.UserEntity;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	PartnerfirmaDAO partnerfirmaDao;
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		System.out.println("im findAllUsers");
		return users;
		
	}
	
	public void setzenStatusStop(Status status) throws Exception{
		partnerfirmaDao.setzenStatusStop(status);
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<>();
		users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
		users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
		users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
		users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
		return users;
	}
	
	public UserEntity getUserByName(String username){
		UserEntity user;
		user = partnerfirmaDao.getUserByName(username);
		return user;
	
	}
	
	public String getAllUserFromDAO(){
		List<StatusEnum> list = new ArrayList<StatusEnum>();
		try{
		list = partnerfirmaDao.getAllesAusStatusEnum();
		if(list.size() ==1){
			return "einzeln";
		}
		else{
			return "mehr";
		}
		
		}catch(Exception e){
		return null;	
		}
	}

}
