package com.example.demo.dto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;
@Data
@Table(name ="user")
@Entity
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int age;
	private String username;
	private String password;
	private String log;
	private String wallet; 
	private String name;
	private String gender;
	private String phone;
	private String homeadress;
	@Transient
	private List<Product> products = new ArrayList<>();
    public List<Product> getProducts() {
		return products;
	}
    public void addProduct(Product product) {
		this.products.add(product);
	}
	public void setProducts(List<Product> products) {
		this.products = products;
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
	public int getId() {
        return id;
    }
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
        return username;
    }
}
