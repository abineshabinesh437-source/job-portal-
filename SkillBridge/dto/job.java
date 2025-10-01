package com.Jobportal.SkillBridge.dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String companyname;
	private long salary;
	private LocalDate date;
	@Column(length = 600)
	private String description;
	private String imagename;
	private String imagetype;
	@Lob
	private byte[] imagedata;
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "userid")
	private user userdata;
	@OneToMany(mappedBy = "jobdata", cascade = CascadeType.ALL,orphanRemoval = true)
	List<jobapplication> apply;
	@OneToMany(mappedBy = "jobdata",cascade = CascadeType.ALL,orphanRemoval = true)
	 private List
	 <commandbox > command;
	
	
 public user getUserdata() {
		return userdata;
	}
	public void setUserdata(user userdata) {
		this.userdata = userdata;
	}
public job( String title, String companyname, long salary, String description, String imagename,
					String imagetype, byte[] imagedata, user userdata,LocalDate date) {
		
		this.title = title;
		this.companyname = companyname;
		this.salary = salary;
		this.description = description;
		this.imagename = imagename;
		this.imagetype = imagetype;
		this.imagedata = imagedata;
		this.userdata = userdata;
		this.date= date;
	}
	public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	
	public job() {
		
	}
	
	

}
