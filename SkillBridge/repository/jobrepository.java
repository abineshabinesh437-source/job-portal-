package com.Jobportal.SkillBridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jobportal.SkillBridge.dto.job;
import com.Jobportal.SkillBridge.dto.user;
@Repository

public interface jobrepository extends JpaRepository<job, Integer> {

	List<job> findByUserdata_Id(int userid);

	

 

}
