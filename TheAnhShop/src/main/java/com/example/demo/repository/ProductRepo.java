package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	List<Product> findByName(String name);
	@Override
	default Product getById(Integer id) {
		return null;
	}
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();
} 