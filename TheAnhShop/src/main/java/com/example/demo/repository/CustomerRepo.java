package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{}
