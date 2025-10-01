package com.Jobportal.SkillBridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Jobportal.SkillBridge.service.commandservice;
@RestController
@RequestMapping("/command")
public class command {
	@Autowired
	commandservice s;
	@PostMapping("/commandbox/{userid}")
	public ResponseEntity<?> commandbox(@PathVariable int userid,@RequestParam int jobid,@RequestParam String command){
		
		return s.command(userid,jobid,command);
		
	}
	@Autowired
	commandservice c;
	@GetMapping("/getcommand")
	public ResponseEntity<?> getcommand(@RequestParam int jobid) {
		
		return c.getingcommand(jobid);
	}
	

}