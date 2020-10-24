package com.venesa.ctvvcare.entity;

import javax.persistence.*;


@Entity
@Table(name= "user_role")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="role_id")
	private int roleId;
	
	
}
