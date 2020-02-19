package com.mkyong;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resources;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.mkyong.service.MyUserDetails;
import com.mkyong.service.UserService;




@Controller
public class HelloWorldController {
	private static String UPLOAD_LOCATION="C:/mytemp/";

	@Autowired
	private ServletContext servletContext;

	@Autowired
	UserService service;
	

	
	@Autowired
	Hintergrund map;
	
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    //@PreAuthorize("hasRole('ADMIN')")
    public String listAllUsers(ModelMap model, HttpServletRequest request) {
    	
    	
    	
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
    	if(auth.getPrincipal() instanceof UserDetails ){
    		Object obj =auth.getPrincipal();
    		MyUserDetails user = (MyUserDetails)obj;
    		System.out.println("USERNAME= "+user.getUsername());
    		List<GrantedAuthority> list = (List<GrantedAuthority>)auth.getAuthorities();
    		for(GrantedAuthority grantedAuthority :list){
    			System.out.println(grantedAuthority.getAuthority());
    		}
    	}
    	
    	String key="Key2";
    	String name = map.getMapHintergrund(key);
    	model.addAttribute("inhab", name);
    	
        List<User> users = service.findAllUsers();
        model.addAttribute("users", users);
        
        //wenn bereits existiert
        //String bild = (String)request.getSession().getAttribute("bild");
        //String bild = request.getContextPath() +"/static/js/sz-card-coupon_org.png";
 	    //model.addAttribute("BILD", bild);
 	    
 	    //getBedingung("ok");
 	   System.out.println("HIER GEKOMMEN------");
        return "allusers";
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}


	private String getPrincipal(){
		String userName;
		String iva;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private String getBedingung(String bedingung){
	
		String ret = null;
		
		switch(bedingung){
		
		case "ok":  ret= "bedingung= " + bedingung;
					return ret;
		case "ok1":  ret= "bedingung1= " + bedingung;
		return ret;
		case "ok2":  ret= "bedingung2= " + bedingung;
		return ret;
		case "ok3":  ret= "bedingung3= " + bedingung;
		return ret;
		case "ok4":  ret= "bedingung4= " + bedingung;
		return ret;
		case "ok5":  ret= "bedingung5= " + bedingung;
		return ret;
		case "ok6":  ret= "bedingung6= " + bedingung;
		return ret;
		case "ok7":  ret= "bedingung7= " + bedingung;
		return ret;
		case "ok8":  ret= "bedingung8= " + bedingung;
		return ret;
		default: return "";
		}
		
	}
}