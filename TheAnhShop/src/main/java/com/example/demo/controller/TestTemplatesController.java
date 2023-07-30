package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestTemplatesController {
	@GetMapping("/test")
	public String tee() {
		return "test1.html";
	}
}
