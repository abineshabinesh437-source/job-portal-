package com.Jobportal.SkillBridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Jobportal.SkillBridge.service.commandservice;
import com.Jobportal.SkillBridge.service.jobpostservice;
import com.Jobportal.SkillBridge.service.userservice;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@RestController
@RequestMapping("/delete")
public class deletecontroller {

	 @Autowired
	 jobpostservice j;
	 @DeleteMapping("/deletejobbyuser/{id}")
	 public ResponseEntity<?> deletejobbyuser(@PathVariable int id,@RequestParam  int jobid){
		 
		 
		 return j.deletejob(id,jobid);
	 }
	 @Autowired
		commandservice si;
	@DeleteMapping("/deletecommand/{userid}")
	   public ResponseEntity<?> deletecommand(@PathVariable int userid,@RequestParam int jobid,@RequestParam int commandid){
			
			
			return si.delete(userid,jobid,commandid);
		}

@Autowired
   userservice u;
	@DeleteMapping("/deleteuser/{userid}")
	public ResponseEntity<?> deleteuser(@PathVariable int userid){
		
		return u.deleteusers(userid); 
	}
}
