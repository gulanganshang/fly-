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

//@WebServlet("/article/loadindex")
public class LoadIndexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleinfoService as=new ArticleinfoServiceImpl();
		List<Map<String, Object>> indexlist=as.checkIndexLoad();
		List<Map<String, Object>> toplist=as.checkTopArt();
		
		
		/*if(indexlist!=null) {
			response.getWriter().println(JSON.toJSONString(indexlist));
			System.out.println(JSON.toJSONString(indexlist));
		}*/
		 request.setAttribute("indexlist", indexlist);
		 request.setAttribute("toplist", toplist);
	     request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
