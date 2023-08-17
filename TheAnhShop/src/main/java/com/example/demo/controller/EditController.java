package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam; // Correct import for RequestParam
import com.example.demo.dto.Product;
import com.example.demo.dto.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
@Controller
public class EditController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@GetMapping("/edit")
	public String edit (@RequestParam("productEditId") String productID, @RequestParam("username") String username, Model model) {
		model.addAttribute("item");
		model.addAttribute("username",username);
		model.addAttribute("sale","0");
		model.addAttribute("category");
		model.addAttribute("product", productService.getById(Integer.parseInt(productID)));
		return "edit.html";
	}
	@GetMapping("/editProduct")
	public String editProductString (@RequestParam("productEditId") String productID,@RequestParam("newName") String newName,
            @RequestParam("newSize") String newSize,
            @RequestParam("newQuantity") String newQuantity,
            @RequestParam("newPrice") int newPrice,
            @RequestParam("newMadeFrom") String newMadeFrom,
            @RequestParam("newColor") String newColor,
            @RequestParam("newNote") String newNote,
            @RequestParam("newUrl") String newUrl,
            @RequestParam("newCategory") String newCategory,
            @RequestParam("newSale") int newSale,
            @RequestParam("username") String username, Model model) {
        productService.updateProductInfo(Integer.parseInt(productID), newName,newSize, newQuantity,
                newPrice, newMadeFrom, newColor, newNote,
                newUrl, newCategory, newSale);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("username",username);
		model.addAttribute("products", products);
		model.addAttribute("item");
		model.addAttribute("sale", "0");
		model.addAttribute("category");
		return "list.html";
	}
	@GetMapping("/new")
	public String newP(@RequestParam("username") String username, Model model){
		List<Product> products = productService.getAllProduct();
		model.addAttribute("username",username);
		model.addAttribute("products", products);
		model.addAttribute("item");
		model.addAttribute("sale", "0");
		model.addAttribute("category");
		return "newProduct.html";
	}
	@GetMapping("/newProduct")
	public String newProduct (@RequestParam("newName") String newName,
            @RequestParam("newSize") String newSize,
            @RequestParam("newQuantity") String newQuantity,
            @RequestParam("newPrice") int newPrice,
            @RequestParam("newMadeFrom") String newMadeFrom,
            @RequestParam("newColor") String newColor,
            @RequestParam("newNote") String newNote,
            @RequestParam("newUrl") String newUrl,
            @RequestParam("newCategory") String newCategory,
            @RequestParam("newSale") int newSale,
            @RequestParam("username") String username, Model model) {
        productService.newProductInfo(newName,newSize, newQuantity,
                newPrice, newMadeFrom, newColor, newNote,
                newUrl, newCategory, newSale);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("username",username);
		model.addAttribute("products", products);
		model.addAttribute("item");
		model.addAttribute("sale", "0");
		model.addAttribute("category");
		return "list.html";
	}
}