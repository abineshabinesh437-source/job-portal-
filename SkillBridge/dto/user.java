package com.Jobportal.SkillBridge.dto;

import java.util.List;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
@Entity
//for creating table name we can use @Table(name="enter the table name" othewise class name will be table name

public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long number;
	private String password;
	private boolean jobaccess;
	
	//image saving 
	private String imagename;
	private String imagetype;
	@Lob // need to mention lob to know ,we can save image only   in byte array 
	private byte[] imagedata;
	
	@OneToMany(mappedBy ="userdata",orphanRemoval = true,cascade = CascadeType.ALL)
	List<job>jobs ;
	@OneToMany(mappedBy = "userdata",orphanRemoval = true,cascade = CascadeType.ALL)
	List<jobapplication> user;
	@OneToMany(mappedBy = "userdata",orphanRemoval = true,cascade = CascadeType.ALL) 
	private List<commandbox>  command;
	public user() {
		
	}
public List<job> getJobs() {
		return jobs;
	}
	public void setJobs(List<job> jobs) {
		this.jobs = jobs;
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public user( String name, String email, long number, String password, String imagename, String imagetype,
			byte[] imagedata) {
		super();
		
		this.name = name;
		this.email = email;
		this.number = number;
		this.password = password;
		this.imagename = imagename;
		this.imagetype = imagetype;
		this.imagedata = imagedata;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getImagetype() {
		return imagetype;
	}
	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}
	public byte[] getImagedata() {
		return imagedata;
	}
	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}
	public boolean isJobaccess() {
		return jobaccess;
	}
	public void setJobaccess(boolean jobaccess) {
		this.jobaccess = jobaccess;
	}
}
