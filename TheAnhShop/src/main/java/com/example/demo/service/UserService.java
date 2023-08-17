package com.example.demo.service;
import com.example.demo.dto.*;
import com.example.demo.repository.UserRepo;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private UserRepo userRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Transactional
    public List<User> getAll() {
        return userRepo.findAll();
    }
    @Transactional
    public void delete(int id) {
        userRepo.deleteById(id);
    }
    @Transactional
    public User getUser(String username) {
    	return userRepo.findByUsername(username);
    }
    @Transactional
    public void create(User user) {
        userRepo.save(user);
    }
    public List<Product> addCart(int productId,String username) {
    	Product product = productService.getById(productId);
    	List<User> users = userRepo.findAll();
    	for (User user : users) {
    		if(username.equals(user.getUsername())) {
    			 user.addProduct(product);
    		}
    	}
    	List<Product> cartProducts = new ArrayList<>();
    	for (User user : users) {
    		if(username.equals(user.getUsername())) {
        		cartProducts = user.getProducts();
    		}
    	}
    	return cartProducts;
    }
}
