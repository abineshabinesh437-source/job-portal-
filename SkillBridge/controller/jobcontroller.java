package com.Jobportal.SkillBridge.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Jobportal.SkillBridge.dto.jobapplication;
import com.Jobportal.SkillBridge.mainDTO.jobapplicationdto;
import com.Jobportal.SkillBridge.mainDTO.jobdto;
import com.Jobportal.SkillBridge.service.jobapplicationservice;
import com.Jobportal.SkillBridge.service.jobpostservice;
@RestController
@RequestMapping("/job")
public class jobcontroller {
	@Autowired
	jobpostservice jobpost;
	 @PostMapping("/addjob/{id}")
	    public ResponseEntity<?> addjob(@PathVariable int id, @RequestParam String title,@RequestParam String companyname,@RequestParam  long salary, @RequestParam String description,@RequestParam (required = false)  MultipartFile imagedata) throws IOException{
	    	
	    	
	    	return jobpost.toaddjob(id,title,companyname,salary,description,imagedata);
	    }
	 @GetMapping("/alljob")
	 public ResponseEntity<List<jobdto>> viewjob(){//jobdto	 
		 
		return jobpost.showjob() ;
		 
	 }
	
	 @Autowired
	 jobapplicationservice app;
	 @PostMapping("/apply/{u_id}/{j_id}")
public ResponseEntity<?>applyjob(@PathVariable int u_id ,@PathVariable int j_id,@RequestBody jobapplicationdto apply)	{
		 
		 
		 return app.jobapply(u_id,j_id,apply);
		 
	 }
	 @Autowired
	 jobpostservice k;
	 @GetMapping("/viewjobforuser/{id}")
	public ResponseEntity<?> viewjobforuser(@PathVariable int id){
		 
		 return k.viewjobbyuser(id);
		 
	 }
	 @Autowired
	 jobpostservice a;
	 @GetMapping("/viewjobbyjobid/{id}")
	 public ResponseEntity<?> viewjobbyjobid(@PathVariable int id){
		 
		 return a.viewjobwithid(id);
	 }
	
	 @Autowired
	 jobapplicationservice s;
	 @GetMapping("/applicationviewbyuserid/{id}")
	 public ResponseEntity<?>applicationviewbyuserid(@PathVariable int id){
		 
		 
		 return s.applicationbyuser(id);
		 
	 }
	 @Autowired
	 jobapplicationservice jk;
	 @GetMapping("/applicationviewbyjobid/{id}")
	 
	 public ResponseEntity<?> applicationviewbyjobid(@PathVariable int id){
		 
		 
		 return jk.viewbyjobid(id);
	 }
	 @Autowired 
	 jobapplicationservice job;
	 @PatchMapping("/updatestatus/{userid}")
	 public ResponseEntity<?> updatestatus(@PathVariable int userid,@RequestParam int jobid,@RequestParam int applicantid,@RequestParam Boolean status){
		 
		 
		 return job.updatestatuss(userid,jobid,applicantid,status);
		 
	 }

}
