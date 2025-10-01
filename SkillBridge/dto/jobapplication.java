package com.Jobportal.SkillBridge.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
//to save the data, not more than one time
@Table(
uniqueConstraints= {@UniqueConstraint(columnNames =  {"u_id","j_id"})})
public class jobapplication {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	@ManyToOne
	@JoinColumn(name="u_id")//apply by
	private user userdata;
	
	@ManyToOne
	@JoinColumn(name="j_id") //job post id
	private job jobdata;
	
	private LocalDate date;
	private String  experience;
	private String currentjobrole;
	private String qualification;
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public jobapplication( user userdata, job jobdata, LocalDate date,  String experience, String currentjobrole,
			String qualification,Boolean status) {
		super();
		//this.id = id;
		this.userdata = userdata;
		this.jobdata = jobdata;
		this.date = date;
		this.experience = experience;
		this.currentjobrole = currentjobrole;
		this.qualification = qualification;
		this.status=status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public user getUserdata() {
		return userdata;
	}
	public void setUserdata(user userdata) {
		this.userdata = userdata;
	}
	public job getJobdata() {
		return jobdata;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getCurrentjobrole() {
		return currentjobrole;
	}
	public void setCurrentjobrole(String currentjobrole) {
		this.currentjobrole = currentjobrole;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public jobapplication() {
		
	}
	

}
