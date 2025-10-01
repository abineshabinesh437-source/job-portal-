package com.Jobportal.SkillBridge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Jobportal.SkillBridge.dto.job;
import com.Jobportal.SkillBridge.dto.jobapplication;
import com.Jobportal.SkillBridge.dto.user;
import com.Jobportal.SkillBridge.mainDTO.jobapplicationdto;
import com.Jobportal.SkillBridge.mainDTO.jobviewdto;
import com.Jobportal.SkillBridge.mainDTO.userdto;
import com.Jobportal.SkillBridge.repository.applicationrepository;
import com.Jobportal.SkillBridge.repository.jobrepository;
import com.Jobportal.SkillBridge.repository.userrepository;

@Service
public class jobapplicationservice {
	@Autowired
	userrepository a;
	@Autowired
	jobrepository b;
	@Autowired
	applicationrepository c;

public ResponseEntity<?> jobapply(int u_id, int j_id, jobapplicationdto apply) {
		Optional<user> userda = a.findById(u_id);
		Optional<job> jobda=  b.findById(j_id);
		if(userda.isPresent()&jobda.isPresent()) {
			user userdata = userda.get();
			System.out.println(userdata);
			job jobdata = jobda.get();
			System.out.println(jobdata);
			jobapplication job = new jobapplication(userdata,
					jobdata,
					LocalDate.now(),
					apply.experience(),
					apply.currentrole(),
					apply.qulification(),
					null);
			try {		
		c.save(job);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("The Application was sent successfully");
			}
		catch (Exception x) {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("you have already applied for this job");
		}			
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong ");
	}

@Autowired
applicationrepository j;
@Autowired
jobrepository r;
@Autowired
 changeimagetostring im;
public ResponseEntity<?> applicationbyuser(int userid) {
	 try {
	        // Get all jobs posted by this user
	        List<job> jobsPostedByUser = r.findByUserdata_Id(userid);
     
	        //  For each job, collect its applications
	        List<jobapplication> allApplications = new ArrayList<>();
	        for (job job : jobsPostedByUser) {
	            List<jobapplication> apps = j.findByJobdata_Id(job.getId());
	            allApplications.addAll(apps);
	        }

	        //  Map to DTO (you can define your own DTO as needed)
	        List<Map<String, Object>> result = allApplications.stream().map(app -> {
	            Map<String, Object> map = new HashMap<>();
	            map.put("jobTitle", app.getJobdata().getTitle());
	            map.put("applicantName", app.getUserdata().getName());
	            map.put("email", app.getUserdata().getEmail());
	            map.put("experience", app.getExperience());
	            map.put("qualification", app.getQualification());
	            map.put("appliedDate", app.getDate());
	          String status = "Pending";
	            if( app.getStatus()!=null) {
	            	if( app.getStatus()) {
	            		
	            		status = "selected";
	            	}
	            	else {
	            		status = "Rejected";
	            	}
	            }
	            map.put("Status",status);
	            return map;
	        }).toList();

	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching applications");
	    }
	
	
}
@Autowired 
applicationrepository jk;
@Autowired
changeimagetostring sh;
public ResponseEntity<?> viewbyjobid(int id) {

	List<jobapplication> apply = jk.findByJobdata_Id(id);
	List<Map<String,Object>> ap = new LinkedList<>();
	if(apply!=null) {
	    for(jobapplication getit :apply) {	
	    	Map<String,Object> app =new HashMap<>();
	    	app.put("NameOfApplicantName", getit.getUserdata().getName());
	    	app.put("Title",getit.getJobdata().getTitle());
	    	app.put("ComapanyName", getit.getJobdata().getCompanyname());
	    	app.put("AppilcationDate", getit.getDate());
	    	//app.put("Salary", getit.getJobdata().getSalary());
	    	
	    	app.put("Number",getit.getUserdata().getNumber());
	    	app.put("Email",getit.getUserdata().getEmail());
	    	app.put("CurrentRole", getit.getCurrentjobrole());
	    	app.put("Experience",getit.getExperience());
	    	app.put("Qulification", getit.getQualification());
	    	app.put("ApplicantPhoto", sh.tostingimage(getit.getUserdata().getImagedata()));
	    	 String status = "Pending";
	            if( getit.getStatus()!=null) {
	            	if( getit.getStatus()) {
	            		
	            		status = "selected";
	            	}
	            	else {
	            		status = "Rejected";
	            	}
	            }
	            app.put("Status",status);
	    	
	    	ap.add(app);
	    }
	
		return ResponseEntity.ok(ap);
		
	}
	
	
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
}
@Autowired
jobrepository ai;
@Autowired
applicationrepository app;
public ResponseEntity<?> updatestatuss(int userid, int jobid,int applicantid,Boolean status) {
	   
	   jobapplication data = app.findByUserdata_IdAndJobdata_Id(applicantid,jobid);
	   
	   if(data!=null) {
	    
	  if(data.getJobdata().getUserdata().getId()==(userid)) {
		  
		  data.setStatus(status);
		  app.save(data);
		  return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully");
		  
		  
	  }
	
	   }
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can't change");
}



}
