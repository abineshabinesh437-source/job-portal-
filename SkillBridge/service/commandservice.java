package com.Jobportal.SkillBridge.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Jobportal.SkillBridge.dto.commandbox;
import com.Jobportal.SkillBridge.dto.job;
import com.Jobportal.SkillBridge.dto.user;
import com.Jobportal.SkillBridge.mainDTO.commandpassdto;
import com.Jobportal.SkillBridge.repository.commandrepository;
import com.Jobportal.SkillBridge.repository.jobrepository;
import com.Jobportal.SkillBridge.repository.userrepository;

import jakarta.transaction.Transactional;

@Service
public class commandservice {
  
	@Autowired
	userrepository a;
	@Autowired
	jobrepository b;
	@Autowired
	commandrepository c;
	public ResponseEntity<?> command(int userid, int jobid,String command) {
		
		Optional<user> userdata = a.findById(userid);
		Optional<job> jobdata = b.findById(jobid);
		if(userdata!=null&&jobdata!=null) {
			
			user userda = userdata.get();
	
			job jobda = jobdata.get();
				
		
		commandbox pass = new commandbox(command,userda,jobda,LocalDate.now());
		c.save(pass);
		return ResponseEntity.ok("done");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
	}
	@Autowired
	commandrepository comm;
	@Autowired
	changeimagetostring imm;
	public ResponseEntity<?> getingcommand(int jobid) {
		List<commandbox>  ge= comm.findByJobdata_Id(jobid);
		if(ge.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("no commands");
			
		}		
		
				
			List<commandpassdto> get = ge.stream().
				map(j->{
					
					commandpassdto com = new commandpassdto(j.getUserdata().getName(),j.getCommand(),imm.tostingimage(j.getUserdata().getImagedata()));
					return com;
				}).toList();
		
		return ResponseEntity.ok(get);
		
		
				
	}
	@Autowired
	commandrepository co;
	@Transactional
	
	public ResponseEntity<?> delete(int userid, int jobid, int commandid) {
		
		commandbox  com = co.findByJobdata_IdAndId(jobid,commandid);
		if(com==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nothing🫥");
			
		}
		if(userid==(com.getUserdata().getId())) {
			
			
			co.deleteByJobdata_IdAndId(jobid,commandid);
			return ResponseEntity.ok("Deleted💕💕💕");
		}
		
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not authorized to delete this command.");
	}
	

}
