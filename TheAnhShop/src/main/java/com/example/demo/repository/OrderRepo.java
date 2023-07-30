package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{}
