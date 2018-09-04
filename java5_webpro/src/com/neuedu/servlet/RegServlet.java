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


//@WebServlet("/user/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		// 调用service查询数据中是否有这个邮箱(servlet-->mapper   servlet-->service-->mapper )
		UserinfoService us = new UserinfoServiceImpl();
		int count = us.checkEmailExists(email);
		response.getWriter().println(count);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("pass");
		// 封装成对象
		Userinfo userinfo = new Userinfo();
		userinfo.setEmail(email);
		userinfo.setNickname(nickname);
		userinfo.setPassword(MD5.MD5(password));
		// 调用对应service，完成插入新用户
		UserinfoService us = new UserinfoServiceImpl();
		int count = us.addNewUserinfo(userinfo);
		if(count == 1) {
			response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/jsp/user/reg.jsp");
		}
	}
}
