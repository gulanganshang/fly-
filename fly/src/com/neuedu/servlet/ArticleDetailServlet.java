package com.neuedu.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.ArticleinfoService;
import com.neuedu.service.ArticleinfoServiceImpl;
import com.sun.org.apache.bcel.internal.generic.InstructionTargeter;
@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String artid=request.getParameter("artid");
	  ArticleinfoService as=new ArticleinfoServiceImpl();
	  Map<String, Object> art_map =as.getIndexArt(Integer.valueOf(artid));
	  //System.out.println(art_map);
	  request.setAttribute("art_map", art_map);
	  request.setAttribute("artid", artid);
	  request.getRequestDispatcher("/jsp/jie/detail.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
