package com.example.demo.service;
import com.example.demo.dto.*;
import com.example.demo.repository.UserRepo;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private UserRepo userRepo;
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
}
