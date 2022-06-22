package com.sns.mars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/main_copy")
	public String mainCopy() {
		return "main_copy";
	}
}
