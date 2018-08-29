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
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		Userinfo userinfo = new Userinfo();
		userinfo.setEmail(email);
		userinfo.setPassword(MD5.MD5(password));
		UserinfoService us=new UserinfoServiceImpl();
		Userinfo user=us.checkLoginUserinfo(userinfo);
		if(user!=null) {
			request.getSession().setAttribute("login_user", user);
			
			response.sendRedirect(request.getContextPath()+"/article/loadindex");
		
		}else {
			request.getSession().setAttribute("msg", "用户名和密码错误");
			request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
		}
	}

}
