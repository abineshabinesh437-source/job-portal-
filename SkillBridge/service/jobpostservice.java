package com.Jobportal.SkillBridge.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Jobportal.SkillBridge.dto.job;
import com.Jobportal.SkillBridge.dto.user;
import com.Jobportal.SkillBridge.mainDTO.jobdto;
import com.Jobportal.SkillBridge.mainDTO.jobviewdto;
import com.Jobportal.SkillBridge.mainDTO.userdto;
import com.Jobportal.SkillBridge.repository.jobrepository;
import com.Jobportal.SkillBridge.repository.userrepository;
@Service
public class jobpostservice {
	@Autowired
	userrepository a;
	@Autowired
	jobrepository b;

	public ResponseEntity<?> toaddjob(int id, String title, String companyname, long salary, String description,
			MultipartFile imagedata) throws IOException {
		Optional<user> checkuser = a.findById(id);
		if(checkuser.isPresent()) {
			user data = checkuser.get();
			if(data.isJobaccess()) {
				job add = new job(title,
						companyname,
						salary,
						description,
						null,
						null,
						null,
						data,
						LocalDate.now());
				
				if(imagedata!=null) {
					add.setImagename(imagedata.getOriginalFilename());
					add.setImagetype(imagedata.getContentType());
					add.setImagedata(imagedata.getBytes());
					
					
				}
				
				b.save(add);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("added successfully");
			}
			else {
				return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("you don't have a access to post jobs");
			}
			
			
			
		}
		
		
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("check your id");
		
	
	}

	public ResponseEntity<List<jobdto>> showjob() {
		//changing job data to jobdto data (job table -> meaning object) to jobdto object
		List<jobdto> jobdata = b.findAll().stream().map(j->{
			
			String jobimage= null;
			String userimage =null;
			if(j.getImagedata()!=null) {
				jobimage = Base64.getEncoder().encodeToString(j.getImagedata());
				
			}
			if(j.getUserdata().getImagedata()!=null) {
				userimage =  Base64.getEncoder().encodeToString(j.getUserdata().getImagedata());
				
			}
			userdto user = new userdto(j.getUserdata().getId(),
					             j.getUserdata().getName(),
					             j.getUserdata().getEmail(),
					             j.getUserdata().getNumber(),
					             null);
					
		jobdto job = new jobdto(j.getId(),
				                j.getTitle(),
				                j.getCompanyname(),
				                j.getSalary(),
				                j.getDescription(),
				                j.getDate(),
				                j.getImagetype(),
				                null,
				                user);		
			
			return job ;}).toList() ;
		System.out.println(jobdata);
		return ResponseEntity.ok(jobdata);
		
		
		
		
	}
	@Autowired
	jobrepository m ;
	public ResponseEntity<?> viewjobbyuser(int userid) {
		
		try {
		List<jobviewdto> jo =  m.findByUserdata_Id(userid).
				stream().
				map(j->{
			
		
			 String image = null;
			 if(j.getImagedata()!=null) {
				 
				 image = Base64.getEncoder().encodeToString(j.getImagedata());
			 }
			jobviewdto jobi= new jobviewdto(j.getTitle(),j.getCompanyname(),j.getSalary(),j.getDate(),j.getDescription(),image);
			
			return jobi;
			
		}).toList();
		
		return ResponseEntity.ok(jo);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some thing went wrong");
		}
		
			
			
	
	}
@Autowired
changeimagetostring img;
	public ResponseEntity<?> viewjobwithid(int id) {
		try {
		 Optional<job>  a = Optional.ofNullable(b.getById(id));
		 if(a.isPresent()) {
			 job jobs = a.get();
			 String image = null;
					 if(jobs.getImagedata()!=null) {
						 
						image= img.tostingimage(jobs.getImagedata());
					 }
			 jobviewdto as = new jobviewdto(jobs.getTitle(),jobs.getCompanyname(),jobs.getSalary(),jobs.getDate(),jobs.getDescription(),
					image);
			 return ResponseEntity.ok(as);
			 
		 }
		}
		catch(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("none of jobs found ");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
	}

	@Autowired
	jobrepository k;
	public ResponseEntity<?> deletejob(int id, int jobid) {
		Optional<job> jo =  k.findById(jobid);
		if(jo.isPresent()) {
			job j = jo.get();
		int jobuserid = j.getUserdata().getId();
		if(jobuserid==id) {
			k.deleteById(jobid);
			return ResponseEntity.ok("Job successfully deleted");
		}
		}
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you can't delete this post");
	}


}
