package com.Jobportal.SkillBridge.service;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class changeimagetostring {
	public String tostingimage(byte[]a) {
		String b = null;
		if(b!=null) {
			b = Base64.getEncoder().encodeToString(a);
			
		}
		return b;
	}

}
