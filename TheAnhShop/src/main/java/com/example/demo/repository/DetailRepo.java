package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Detail;
import java.util.List;


public interface DetailRepo extends JpaRepository<Detail, Integer> {
	List<Detail> findByOid(int oid);
	void deleteById(int id);
}
