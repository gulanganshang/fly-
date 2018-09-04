package com.neuedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/loadreg")
	public String loadreg() {
		return "user/reg";
	}
	
	@RequestMapping("/loadlogin")
	public String loadlogin() {
		return "user/login";
	}
	
	@RequestMapping("/add")
	public String loadadd() {
		return "jie/add";
	}
}
