package com.neuedu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/jie/goadd")
	public String jieAdd(HttpSession session) {
		Object login_user = session.getAttribute("login_user");
		if (login_user != null) {
			// response.sendRedirect(request.getContextPath() + "/jsp/jie/add.jsp");
			return "jie/add";
		} else {
			// response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
			return "user/login";
		}

	}
}
