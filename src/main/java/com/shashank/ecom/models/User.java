package com.shashank.ecom.models;

import jakarta.persistence.Entity;
@Entity
public class User extends BaseModel{
	private String name;
	private String email;
	private String phone;
	private Gender gender;
	public User(String name, String email, String phone,
			Gender gender) {
		
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
	}
	public User() {
		
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
