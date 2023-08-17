package com.example.demo.service;

import java.util.List;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.demo.dto.Order;
import com.example.demo.dto.Product;
import com.example.demo.dto.User;
import com.example.demo.repository.OrderRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	OrderRepo orderRepo;
	@Autowired
	public OrderService(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}
	@Transactional
	public List<Order> getAll(){
		return orderRepo.findAll();
	}
	@Transactional
	public Order getByCustomerID(String customerid){
		return orderRepo.findByCustomerid(customerid);
	}
	@Transactional
	public void create(Order order) {
		orderRepo.save(order);
	}
	@Transactional
	public void add(List<Product> products,int price,int cusId ) {
		Order order = new Order();
		order.setTime(Timestamp.valueOf(LocalDateTime.now()));
		order.setPrice(price);
		order.setCustomerid(price);
		order.setCustomerid(cusId);
		orderRepo.save(order);
	}
}
