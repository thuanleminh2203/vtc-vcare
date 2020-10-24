package com.venesa.ctvvcare.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@JsonSerialize
public class EmailDTO {
	private String myEmail;
	private String password;
	private List<String> toListEmail;
	private String content;
	private String subject;
	private MultipartFile[] multipartFiles;
	
	
	public MultipartFile[] getMultipartFiles() {
		return multipartFiles;
	}
	public void setMultipartFiles(MultipartFile[] multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
	public String getMyEmail() {
		return myEmail;
	}
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getToListEmail() {
		return toListEmail;
	}
	public void setToListEmail(List<String> toListEmail) {
		this.toListEmail = toListEmail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
