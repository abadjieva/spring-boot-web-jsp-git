package com.mkyong;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Hintergrund {

	Map<String, String> map = new HashMap<String,String>();
	String key1="Key1";
	String key2="Key2";
	String val1="IvaAba";
	String val2="Iva Todorova Abadjieva";
	public Hintergrund(){
		map.put(key1, val1);
		map.put(key2, val2);
		
	}
	public String getMapHintergrund(String key){
		String value = map.get(key);
		return value;
	}
}
