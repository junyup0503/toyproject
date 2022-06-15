package com.sns.snstoy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mars")
public class MemberController {

	@GetMapping("/")
	public String login() {
		return "index";
	}
	
	@PostMapping("/login")
	public String login(
						@RequestParam String memberId,
						@RequestParam String memberPw,
						HttpSession session
						) {
		
		return "redirect:/mars/main";
	}
}
