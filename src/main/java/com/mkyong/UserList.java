package com.mkyong;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserList {

	@XmlElement(name = "User", type = User.class)
	List<User> liste = new ArrayList<User>();
	

	public List<User> getList() {
		return liste;
	}

//	public void setList(List<User> liste) {
//		this.liste = liste;
//	}
	
	
}
