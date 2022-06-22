package com.sns.mars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DMController {
	
	@GetMapping("/dm/dm")
	public String dm() {
		return "dm/dm";
	}
	
	@GetMapping("/dm/dm_copy")
	public String dm_copy() {
		return "dm/dm_copy";
	}
	
	@GetMapping("/dm/dm_copy/{a}")
	public String dm_copy(
					@PathVariable(required = false) int a) {
		return "dm/dm_copy";
	}

	
}
