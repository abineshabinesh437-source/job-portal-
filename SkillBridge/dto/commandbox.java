package com.Jobportal.SkillBridge.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class commandbox {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
     private String command;
     @ManyToOne
     @JoinColumn(name="userdata")
     private user userdata;
     @ManyToOne
     @JoinColumn(name="jobdata")
     private job jobdata;
     private LocalDate date ;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate data) {
		this.date = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
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
	public commandbox(String command, user userdata, job jobdata,LocalDate date) {
		super();
		this.command = command;
		this.userdata = userdata;
		this.jobdata = jobdata;
		this.date=date;
	}
	public void setJobdata(job jobdata) {
		this.jobdata = jobdata;
	}
	public commandbox() {
		
	}
}
