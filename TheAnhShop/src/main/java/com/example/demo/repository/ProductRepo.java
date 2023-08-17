package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.dto.Product;
import org.springframework.data.domain.Sort;

public interface ProductRepo extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> searchByName(String name);
    @Query("SELECT p FROM Product p WHERE p.category LIKE %:category%")
    List<Product> searchByCategory(String category);
    @Query("SELECT p FROM Product p WHERE p.color LIKE %:color%")
    List<Product> searchByColor(String color);
}