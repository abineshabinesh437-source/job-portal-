package com.Jobportal.SkillBridge.repository;

import org.aspectj.weaver.tools.JoinPointMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.Jobportal.SkillBridge.dto.user;
import com.Jobportal.SkillBridge.mainDTO.registerdto;
import com.Jobportal.SkillBridge.mainDTO.userdto;
//manage database req
@Repository
@Component
   
public interface userrepository extends JpaRepository<user, Integer> {

	public user findByEmail(String email);

    //  public userdto findByEmail(String email,String password);
	
	
	
	

}
