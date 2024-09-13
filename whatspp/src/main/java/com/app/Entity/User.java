package com.app.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String full_Name;
	private String profile_picture;
	private String password;
	
	
	public User() {
		super();
	}
	
	public User(Long id, String email, String full_Name, String profile_picture, String password) {
		super();
		this.id = id;
		this.email = email;
		this.full_Name = full_Name;
		this.profile_picture = profile_picture;
		this.password = password;
	}




	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFull_Name() {
		return full_Name;
	}
	
	public void setFull_Name(String full_Name) {
		this.full_Name = full_Name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", full_Name=" + full_Name + ", profile_picture=" + profile_picture + ", password="
				+ password + "]";
	}
	
	
	
	
}
