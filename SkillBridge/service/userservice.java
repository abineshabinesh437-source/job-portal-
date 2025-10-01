package com.Jobportal.SkillBridge.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.Jobportal.SkillBridge.dto.user;
import com.Jobportal.SkillBridge.mainDTO.adminuserviewdto;
import com.Jobportal.SkillBridge.mainDTO.registerdto;
import com.Jobportal.SkillBridge.mainDTO.userdto;
import com.Jobportal.SkillBridge.repository.userrepository;

@Service

public class userservice {
	@Autowired
	userrepository a;
	public boolean data(registerdto reguser) {
		user done = a.findByEmail(reguser.email());
		if(done==null) {
			user newuser = new user( reguser.name(), reguser.email(), reguser.number(), reguser.password() , null, null,
					null) ;
			System.out.println(reguser.number());
			a.save(newuser);
			return true;
		}
		else {
			return false;
		}
	}
   //ResponseEntity<?> return any type 
	public  ResponseEntity<?>  checkuser(registerdto loginuser) {
		String email = loginuser.email();
		String password = loginuser.password();
			user data =a.findByEmail(email); 
			
			
			if(data!=null) {
				String checkpassword = data.getPassword();
			userdto userdata = new userdto(data.getId(),data.getName(),data.getEmail(),data.getNumber(),null);
			if(password.equals(checkpassword)) {
				  return ResponseEntity.ok(userdata);
				  }
				  else {
					  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong  password");
				  }
			}
	
				  return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Kindly register First");		
	}
	public ResponseEntity<?> updatedata(String name, String email, long number, int id, MultipartFile imagedata) {
		
		Optional<user> checked = a.findById(id);
	
		if(checked.isPresent()) {
			user existuser = checked.get();
		
			existuser.setName(name);
			existuser.setEmail(email);
			existuser.setNumber(number);
			//check null , this is the process to save the image in data base name and type is obtional
			if(imagedata!=null) {
				existuser.setImagename(imagedata.getOriginalFilename());
				existuser.setImagetype(imagedata.getContentType());
				try {
					existuser.setImagedata(imagedata.getBytes());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				a.save(existuser);
				
			}
		String 	image = null;
		if(existuser.getImagedata()!=null) {
			//for sending image as string, we can only sent the image from database as string and save by byte []
			image = Base64.getEncoder().encodeToString(existuser.getImagedata());
			userdto user = new userdto(existuser.getId(),existuser.getName(),existuser.getEmail(),existuser.getNumber(),image);
			
			return ResponseEntity.ok(user);
		}		
		}	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid ") ;
	}
	public ResponseEntity<?> checkandupdatepassword(int id, String email, String oldpassword,String newpassword) {
		Optional  <user> check = a.findById(id);
		if(check.isPresent()) {
			
	      user data = check.get();
	      if(!email.equals(data.getEmail())) {
	    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id or email");
	      }
	      if(email.equals(data.getEmail())&&data.getPassword().equals(oldpassword)){
	    	  
	    	  data.setPassword(newpassword);
	    	  a.save(data);
	    	  return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password changed successfully");
	      }
	      else {
	    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email Id or password Wrong");
	      }
	      		
		}
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
	}
	@Autowired
	userrepository u;
	@Autowired
	changeimagetostring i;
	public ResponseEntity<?> alluser() {
		
		Optional<List<user>> a = Optional.ofNullable(u.findAll());
		if(a.isPresent()) {
			List<adminuserviewdto> data = a.get().stream().map(j->{
				
				String image = null;
						if(j.getImagedata()!=null) {
							
							image = i.tostingimage(j.getImagedata());
							
						}
						adminuserviewdto da = new adminuserviewdto(j.getId(),
								j.getName(),
								j.getEmail()
								,j.getNumber()
								,j.isJobaccess()
								,j.getPassword()
								,j.getImagename()
								,j.getImagetype()
								,image);
				return da;
			}).toList();
			return ResponseEntity.ok(data);
			
			
			
		}
		
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("no user found");
	}
	@Autowired
	userrepository user;
	public ResponseEntity<?> deleteusers(int userid) {
		
		Optional<user> s = user.findById(userid);
		if(s.isPresent()) {
			
			user.deleteById(userid);
			
		return	ResponseEntity.ok("Deleted");
		}
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
	}
	
	}
	
	
	
	
	
	
	
	

	

	
	


