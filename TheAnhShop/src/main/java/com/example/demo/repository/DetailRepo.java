package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Detail;

public interface DetailRepo extends JpaRepository<Detail, Integer> {}
