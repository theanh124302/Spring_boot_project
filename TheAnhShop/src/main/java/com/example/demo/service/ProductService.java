package com.example.demo.service;
import com.example.demo.dto.*;
import com.example.demo.repository.ProductRepo;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Transactional
    public List<Product> getAll() {
        return productRepo.findAll();
    }
    @Transactional
    public void delete(int id) {
    	productRepo.deleteById(id);
    }
    @Transactional
    public List<Product> getProduct(String name) {
    	return productRepo.findByName(name);
    }
    @Transactional
    public void create(Product product) {
    	productRepo.save(product);
    }
    @Transactional
    public List<Product> getAllSortedByPriceAscending() {
        return productRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
    @Transactional
    public List<Product> getAllSortedByPriceDescending() {
        return productRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }
}
