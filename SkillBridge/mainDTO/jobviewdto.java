package com.Jobportal.SkillBridge.mainDTO;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;



public record jobviewdto(String titile,
		String company,
		long salary,
		LocalDate date, 
		String description,
		String image) {

}
