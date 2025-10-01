package com.Jobportal.SkillBridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jobportal.SkillBridge.dto.job;
import com.Jobportal.SkillBridge.dto.jobapplication;

public interface applicationrepository extends JpaRepository<jobapplication, Integer>{

	List<jobapplication> findByUserdata_Id(int userid);

	List<jobapplication> findByJobdata_Id(int id);

	jobapplication findByUserdata_IdAndJobdata_Id(int userid, int jobid);

	
	

}