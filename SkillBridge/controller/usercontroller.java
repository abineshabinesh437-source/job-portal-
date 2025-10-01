package com.Jobportal.SkillBridge.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.GetExchange;


import com.Jobportal.SkillBridge.mainDTO.registerdto;
import com.Jobportal.SkillBridge.mainDTO.userdto;
import com.Jobportal.SkillBridge.service.jobpostservice;
import com.Jobportal.SkillBridge.service.userservice;

import jakarta.persistence.Column;
@CrossOrigin(origins = " http://localhost:5173/")
@RestController
@RequestMapping("/user")
public class usercontroller{
	
		@Autowired
		userservice service ;
		@Autowired
		jobpostservice jobpost;
		@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody registerdto reguser){
			System.out.println(reguser.number());
		boolean a = service.data(reguser);
		if(a) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("user registered successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already there");
		}
		
		}

       @PostMapping("/login")
public ResponseEntity<?> login (@RequestBody registerdto loginuser ){
	   
	       
  return service.checkuser(loginuser);
 
   }
 
       @PostMapping("/update/{id}")
public ResponseEntity<?> update
(@RequestParam String name, @RequestParam String email,@RequestParam long phone,@RequestParam int id,@RequestParam (required = false) MultipartFile imagedata){
	   
    	
    	
    	
     return service.updatedata(name,email,phone,id,imagedata);
    }


       @PostMapping("/updatepassword/{id}")
public ResponseEntity<?> updatepassword
(@PathVariable int id,@RequestParam String email, @RequestParam String oldpassword,@RequestParam String newpassword){
    	
    	return service.checkandupdatepassword(id,email,oldpassword,newpassword);
    }
   @Autowired
   userservice u;
    
    @GetMapping("/viewalluser")
    public ResponseEntity<?> viewalluser(){
    	
    	return u.alluser();
    	
    }
  
    
    
}
