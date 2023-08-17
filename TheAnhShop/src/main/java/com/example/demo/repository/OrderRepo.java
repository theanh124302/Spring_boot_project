package com.example.demo.repository;

import org.springframework.data.aot.PublicMethodReflectiveProcessor;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Order;
import java.util.List;


public interface OrderRepo extends JpaRepository<Order, Integer>{
	Order findByCustomerid(String customerid);
}
