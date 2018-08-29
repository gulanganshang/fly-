package com.neuedu.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.neuedu.service.ArticleinfoService;
import com.neuedu.service.ArticleinfoServiceImpl;



@WebServlet("/article/host")
public class ArticleHostServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleinfoService as=new ArticleinfoServiceImpl();
		List<Map<String, Object>> host_map=as.checkHostArt();
		System.out.println(JSON.toJSONString(host_map));
		response.getWriter().println(JSON.toJSONString(host_map));
	}

}
