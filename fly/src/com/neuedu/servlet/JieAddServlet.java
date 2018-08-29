package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Artcletype;
import com.neuedu.service.ArtcletypeService;
import com.neuedu.service.ArtcletypeServiceImpl;

@WebServlet("/jie/goadd")
public class JieAddServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object login_user = request.getSession().getAttribute("login_user");
		if (login_user != null) {
			response.sendRedirect(request.getContextPath() + "/jsp/jie/add.jsp");
		} else {
            response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  
		    
	}

}
