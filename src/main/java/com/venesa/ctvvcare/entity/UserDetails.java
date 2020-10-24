package com.venesa.ctvvcare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	@Column
	private Integer id;

	@Column
	private String fullname;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "khoa")
	private int khoa;
	
	@Column(name = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name = "id_user")
	@JsonIgnore
	private int idUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getKhoa() {
		return khoa;
	}

	public void setKhoa(int khoa) {
		this.khoa = khoa;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
}
