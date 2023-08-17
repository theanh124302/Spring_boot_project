package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.ProductRepo;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

@Service
public class ProductService {
	private ProductRepo productRepo;
	Map<Integer, Integer> productCounts = new HashMap<>();
	@Autowired
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	@Transactional
	public void create(Product product) {
		productRepo.save(product);
	}
	@Transactional
	public Product getById(int id) {
		return productRepo.findById(id).orElse(null);
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
	public List<Product> getAllProduct() {
		List<Product> AllProducts = productRepo.findAll();
		return AllProducts;
	}

	@Transactional
	public List<Product> getProductByName(String name) {
		List<Product> listProducts = productRepo.searchByName(name);
		return listProducts;
	}
	
	@Transactional
	public List<Product> getProductByCategory(String category) {
		List<Product> listProducts = productRepo.searchByCategory(category);
		return listProducts;
	}

	@Transactional
	public List<Product> getProductByColor(String color) {
		List<Product> listProducts = productRepo.searchByColor(color);
		return listProducts;
	}
	
	@Transactional
	public List<Product> getAllSale() {
		List<Product> AllProducts = productRepo.findAll();
		AllProducts.removeIf(product -> product.getSale() == 0);
		return AllProducts;
	}
	
	@Transactional
	public List<Product> sortProduct(List<Product> product, String sort) {
		if (sort.equals("desc")) {
			Collections.sort(product, Comparator.comparing(Product::getPrice).reversed());
		} else {
			Collections.sort(product, Comparator.comparing(Product::getPrice));
		}
		return product;
	}
	@Transactional
    public Product updateProductInfo(int productId, String newName, String newSize, String newQuantity,
            int newPrice, String newMadeFrom, String newColor, String newNote,
            String newUrl, String newCategory, int newSale) {
		Product product = productRepo.findById(productId).orElse(null);
		if (product != null) {
			product.setName(newName);
			product.setSize(newSize);
			product.setQuantity(newQuantity);
			product.setPrice(newPrice);
			product.setMadefrom(newMadeFrom);
			product.setColor(newColor);
			product.setNote(newNote);
			product.setUrl(newUrl);
			product.setCategory(newCategory);
			product.setSale(newSale);
			return productRepo.save(product);
		}
		return null;
}
	@Transactional
    public Product newProductInfo(String newName, String newSize, String newQuantity,
            int newPrice, String newMadeFrom, String newColor, String newNote,
            String newUrl, String newCategory, int newSale) {
		Product product = new Product();
		product.setName(newName);
		product.setSize(newSize);
		product.setQuantity(newQuantity);
		product.setPrice(newPrice);
		product.setMadefrom(newMadeFrom);
		product.setColor(newColor);
		product.setNote(newNote);
		product.setUrl(newUrl);
		product.setCategory(newCategory);
		product.setSale(newSale);
		return productRepo.save(product);
	}
	@Transactional
	public int Sum(List<Product> products) {
		int sum = 0;
		if(products!=null) {
			for (Product product : products) {
				sum += product.getPrice();
			}
		}
		return sum;
	}
	@Transactional
	public boolean Compare(List<Product> products) {
		if(products!=null) {
			for (Product product : products) {
				product.setQuantity("0");
			}
		}
		if(products!=null) {
			for (Product product : products) {
				product.setQuantity("0");
			}
		}
		if(products!=null) {
			for (Product product : products) {
				int productId = product.getId();
	            productCounts.put(productId, productCounts.getOrDefault(productId, 0) + 1);
			}
		}
		return true;
	}
}
