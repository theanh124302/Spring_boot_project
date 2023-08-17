package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam; // Correct import for RequestParam
import com.example.demo.dto.Product;
import com.example.demo.dto.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ListController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@GetMapping("/list")
	public String findByname(@RequestParam("item") String item, @RequestParam("username") String username,@RequestParam("category") String category,@RequestParam("sale") String sale,
			@RequestParam(value = "sort", required = false, defaultValue = "asc") String sort, Model model, HttpSession session) {
		String[] splitItems = item.split(" ");
		model.addAttribute("productsCart",(List<Product>) session.getAttribute("cartProductIds"));
		model.addAttribute("priceSum", productService.Sum((List<Product>) session.getAttribute("cartProductIds")));
		model.addAttribute("username",username);
		List<User> users = userService.getAll();
		List<Product> cartProducts = new ArrayList<>();
    	for (User user : users) {
    		if(username.equals(user.getUsername())) {
        		cartProducts = user.getProducts();
        		model.addAttribute("cartProducts", cartProducts);
    		}
    	}
		List<Product> products = new ArrayList<>();
		if(!category.equals("")){
			products.addAll(productService.getProductByCategory(category));
		}
		if (sale.equals("1")){
			products.addAll(productService.getAllSale());
		}
		if(!item.equals("")){
			for (String searchString : splitItems) {
				products.addAll(productService.getProductByName(searchString));
				products.addAll(productService.getProductByCategory(searchString));
				products.addAll(productService.getProductByColor(searchString));
			}
		}
		Map<Integer, Product> productMap = new HashMap<>();
		for (Product product : products) {
		    if (!productMap.containsKey(product.getId())) {
		        productMap.put(product.getId(), product);
		    }
		}
		products = new ArrayList<>(productMap.values());
		products = productService.sortProduct(products, sort);
		model.addAttribute("products", products);
		if(products.size()>0) {
			model.addAttribute("item", item);
			model.addAttribute("sale", sale);
			model.addAttribute("category", category);
			return "list.html";
		}
		else {
			products = productService.getAllProduct();
			products = productService.sortProduct(products, sort);
			model.addAttribute("item", item);
			model.addAttribute("sale", sale);
			model.addAttribute("category", category);
			model.addAttribute("products", products);
			return "list.html";
		}
	}

	@GetMapping("/list/all") 
	public String findBycategory(@RequestParam(value = "sort", required = false, defaultValue = "asc") String sort, Model model, @RequestParam("username") String username, HttpSession session) {
		List<Product> products = productService.getAllProduct();
		products = productService.sortProduct(products, sort);
		model.addAttribute("username",username);
		model.addAttribute("productsCart",(List<Product>) session.getAttribute("cartProductIds"));
		model.addAttribute("priceSum", productService.Sum((List<Product>) session.getAttribute("cartProductIds")));
		model.addAttribute("products", products);
		model.addAttribute("item");
		model.addAttribute("sale", "0");
		model.addAttribute("category");
		return "list.html";
	}
	
	@GetMapping("/list/category") 
	public String findBycategory(@RequestParam("category") String category,@RequestParam(value = "sort", required = false, defaultValue = "asc") String sort, Model model, @RequestParam("username") String username, HttpSession session) {
		List<Product> products = productService.getProductByCategory(category);
		products = productService.sortProduct(products, sort);
		model.addAttribute("username",username);
		model.addAttribute("productsCart",(List<Product>) session.getAttribute("cartProductIds"));
		model.addAttribute("priceSum", productService.Sum((List<Product>) session.getAttribute("cartProductIds")));
		model.addAttribute("products", products);
		model.addAttribute("item");
		model.addAttribute("sale", "0");
		model.addAttribute("category", category);
		return "list.html";
	}
	
	@GetMapping("/list/sale") 
	public String findBysale(@RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,Model model, @RequestParam("username") String username, HttpSession session) {
		List<Product> products = productService.getAllSale();
		model.addAttribute("username",username);
		products = productService.sortProduct(products, sort);
		model.addAttribute("products", products);
		model.addAttribute("productsCart",(List<Product>) session.getAttribute("cartProductIds"));
		model.addAttribute("priceSum", productService.Sum((List<Product>) session.getAttribute("cartProductIds")));
		model.addAttribute("sale", "1");
		model.addAttribute("item");
		model.addAttribute("category");
		return "list.html";
	}
	@GetMapping("/addcart")
	public String addCart(@RequestParam("productCartId") String productID,Model model, @RequestParam("username") String username, HttpSession session) {
		List<Product> cartProductIds = (List<Product>) session.getAttribute("cartProductIds");
		List<Product> Products = productService.getAll();
        if (cartProductIds == null) {
            cartProductIds = new ArrayList<>();
        }
		for(Product product : Products) {
			for (Product product2 : cartProductIds) {
				if(product2.getId()==product.getId()) {
					product2.setQuantity(String.valueOf(Integer.parseInt(product2.getQuantity())-1));
				}
				if (Integer.parseInt(product2.getQuantity())<1) {
			        session.setAttribute("cartProductIds", cartProductIds);
					model.addAttribute("productsCart",cartProductIds);
					model.addAttribute("priceSum", productService.Sum(cartProductIds));
					model.addAttribute("username",username);
					model.addAttribute("item");
					model.addAttribute("sale","0");
					model.addAttribute("category");
					model.addAttribute("products", productService.getAll());
					return "list.html";
				}
			}
		}
        cartProductIds.add(productService.getById(Integer.parseInt(productID)));
        session.setAttribute("cartProductIds", cartProductIds);
		model.addAttribute("productsCart",cartProductIds);
		model.addAttribute("priceSum", productService.Sum(cartProductIds));
		model.addAttribute("username",username);
		model.addAttribute("item");
		model.addAttribute("sale","0");
		model.addAttribute("category");
		model.addAttribute("products", productService.getAll());
		return "list.html";
	}
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("productDeleteId") String productID,Model model, @RequestParam("username") String username, HttpSession session) {
		productService.delete(Integer.parseInt(productID));
		model.addAttribute("username",username);
		model.addAttribute("item");
		model.addAttribute("sale","0");
		model.addAttribute("category");
		model.addAttribute("products", productService.getAllProduct());
		return "list.html";
	}
	
	@GetMapping("/buy")
	public String buyProduct(Model model, @RequestParam("username") String username, HttpSession session) {
		List<Product> cartProductIds = (List<Product>) session.getAttribute("cartProductIds");
		session.removeAttribute("cartProductIds");
		int userId = 0;
		List<User> users = userService.getAll();
    	for (User user : users) {
    		if(username.equals(user.getUsername())) {
    			userId = user.getId();
    		}
    	}
    	orderService.add(cartProductIds,productService.Sum(cartProductIds), userId);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("username",username);
		model.addAttribute("products", products);
		model.addAttribute("item");
		model.addAttribute("sale", "0");
		model.addAttribute("category");
		return "list.html";
	}
}
