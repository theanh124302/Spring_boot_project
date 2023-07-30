package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
@Data
@Table(name ="customer")
@Entity
public class Customer {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int age;
	private String name;
	@Column(unique = true)
	private int userid;
	private String gender;
	private String phone;
	private String homeadress;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHomeadress() {
		return homeadress;
	}
	public void setHomeadress(String homeadress) {
		this.homeadress = homeadress;
	}
}