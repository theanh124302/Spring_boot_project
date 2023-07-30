package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	//List<User> findByName(String name);
	User findByUsername(String username);
	void deleteById(int id);
} 
