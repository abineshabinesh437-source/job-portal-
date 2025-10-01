package com.Jobportal.SkillBridge.mainDTO;

import java.time.LocalDate;

import com.Jobportal.SkillBridge.dto.user;

public record jobdto
(int id,String titile,String companyname, long salary,String description,LocalDate date,String imagetype,String imagedata,userdto userdata) {

}
