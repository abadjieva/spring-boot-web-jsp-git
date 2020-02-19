package com.mkyong;
 
import java.util.ArrayList;
import java.util.List;
 


















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.dao.PartnerfirmaDAO;
import com.mkyong.dao.Status;
import com.mkyong.service.UserService;
 
 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    @Autowired   
    PartnerfirmaDAO dao;
     
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/alluser/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserList> listAllUsers() {
    	System.out.println("----- alluser");
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
        	System.out.println("----- alluser1");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("----- alluser2");
        
        List<User> userss = new ArrayList<>();
        User u1= new User(20, "Sam",30, 70000);
        User u2= new User(21, "Iva",40, 80000);
        userss.add(u1);
        userss.add(u2);
        
      UserList lis = new UserList();
      lis.getList().addAll(userss);
        return new ResponseEntity<UserList>(lis, HttpStatus.OK);
        
        
       // return new ResponseEntity<>(users,HttpStatus.OK);
        
        
    }
    
    @RequestMapping(value = "/greeting/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    
 //   @RequestMapping("/greeting/")
    public ResponseEntity<User> greeting(@RequestParam(value="name") String name)
    {
    	System.out.println("----- greeting");
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
        
        User user = userService.findByName(name);
        
        if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        try{
        dao.setzenStatusStop(Status.START);
        System.out.println("----- nach dem Setzen Status");
        
        ResponseEntity<User> resp=new ResponseEntity<>(user, HttpStatus.OK);
        System.out.println(resp.getBody().toString());
        System.out.println(resp.getStatusCode().toString());
        System.out.println(resp.getHeaders().toString());
        return resp;
       
        //throw new Exception("KünstlichEXC");
        }catch(Exception e){
        	System.out.println(e.getMessage());
        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        
 
    }
 
 
}