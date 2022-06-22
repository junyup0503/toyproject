package com.sns.mars.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.mars.entity.MemberDto;
import com.sns.mars.repository.MemberDao;

@Controller
public class MemberController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/")
	public String login() {
		return "home";
	}
	
	@PostMapping("/login")
	public String login(
						@RequestParam String memberEmail,
						@RequestParam String memberPw,
						HttpSession session
						) {
		MemberDto memberDto = memberDao.login(memberEmail, memberPw);
		System.out.println(memberDto.getMemberEmail());
		if(memberDto != null) {
			session.setAttribute("memberNo", memberDto.getMemberNo());
			return "redirect:/main";
		}else {
			return "redirect:/?error";
		}
	}
	
	
	
	@GetMapping("/member/my_page")
	public String mypage() {
		return "member/my_page";
	}
	
	@GetMapping("/member/my_page_copy")
	public String mypageCopy() {
		return "member/my_page_copy";
	}
	
	
}
