package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.Product;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;
	@GetMapping("/home")
	public String tee(Model model, HttpSession session) {
		model.addAttribute("productsCart",(List<Product>) session.getAttribute("cartProductIds"));
		model.addAttribute("priceSum", productService.Sum((List<Product>) session.getAttribute("cartProductIds")));
		return "home.html";
	}
}
