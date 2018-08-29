package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.UserinfoServiceImpl;
import com.neuedu.util.MD5;

@WebServlet("/user/reg")
public class RegServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println(email);
		UserinfoService um = new UserinfoServiceImpl();
		int count = um.checkEmailExists(email);
		response.getWriter().println(count);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Userinfo userinfo = new Userinfo();
		userinfo.setNickname(nickname);
		userinfo.setEmail(email);
		userinfo.setPassword(MD5.MD5(password));
		UserinfoService us = new UserinfoServiceImpl();
		int cou=us.addNewUserinfo(userinfo);
		if(cou == 1) {
			request.getSession().setAttribute("reg_email", email);
			response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/jsp/user/reg.jsp");
		}
		
	}

}
