package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import org.hibernate.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // Correct import for RequestParam
import com.example.demo.dto.Product;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ListController {
	private String itemClone;
	@Autowired
	private ProductService productService;
	@GetMapping("/list")
	public String findByname(@RequestParam("item") String item,@RequestParam(value = "sort", required = false, defaultValue = "asc") String sort, Model model) {
		List<Product> products = productService.getProduct(item);
		model.addAttribute("products", products);
		if(products.size()>0) {
			return "list.html";
		}
		else {
			products = productService.getAll();
			model.addAttribute("products", products);
			return "list.html";
		}
	}
	@GetMapping("/list/category") 
	public String categoryPage(HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp,
			jakarta.servlet.http.HttpSession session
			)throws IOException{
		return "list.html";
	}

}


/*
 * @GetMapping("/list/sorted") public String
 * listSortedPage(@RequestParam("sort") String sortType, Model model) {
 * List<Product> sortedProducts; if ("asc".equals(sortType)) { sortedProducts =
 * productService.getAllSortedByPriceAscending(); } else if
 * ("desc".equals(sortType)) { sortedProducts =
 * productService.getAllSortedByPriceDescending(); } else { sortedProducts =
 * productService.getAll(); } model.addAttribute("products", sortedProducts);
 * return "list.html"; }
 */
