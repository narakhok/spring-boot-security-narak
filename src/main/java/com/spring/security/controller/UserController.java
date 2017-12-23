package com.spring.security.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {	
	

	@GetMapping("/user")
	public String user() {	
		return "user/dashboard";
	}
	
	
	@GetMapping("/admin")
	public String admin() {	
		return "admin/dashboard";
	}
	
	@GetMapping("/dba")
	public String dba() {	
		return "dba/dashboard";
	}
	
	@GetMapping(value={"","/"})
	public String index() {	
		return "index";
	}
	
	
	@GetMapping("/login")
	public String login() {	
		return "login";
	}
}
