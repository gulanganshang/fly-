package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.impl.UserinfoServiceImpl;
import com.neuedu.util.MD5;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		Userinfo userinfo = new Userinfo();
		userinfo.setEmail(email);
		userinfo.setPassword(MD5.MD5(password));
		// 调service查询是否可以登录
		UserinfoService us = new UserinfoServiceImpl();
		Userinfo user = us.checkLoginUser(userinfo);
		if(user != null) {
			// 可以登录
			request.getSession().setAttribute("login_user", user);
			response.sendRedirect(request.getContextPath()+"/article/loadindex");
		}else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
		}
		
	}

}
