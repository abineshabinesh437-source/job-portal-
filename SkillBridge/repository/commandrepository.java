package com.Jobportal.SkillBridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jobportal.SkillBridge.dto.commandbox;

public interface commandrepository extends JpaRepository<commandbox, Integer> {

	List<commandbox> findByJobdata_Id(int jobid);

	commandbox findByJobdata_IdAndId(int jobid, int commandid);

	void deleteByJobdata_IdAndId(int jobid, int commandid);

}
