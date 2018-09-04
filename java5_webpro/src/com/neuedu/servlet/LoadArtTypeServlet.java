package com.neuedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.neuedu.bean.Artcletype;
import com.neuedu.service.ArticleService;
import com.neuedu.service.impl.ArticleServiceImpl;

/**
 * Servlet implementation class LoadArtTypeServlet
 */
//@WebServlet("/article/type")
public class LoadArtTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查询所有帖子分类
		ArticleService as = new ArticleServiceImpl();
		List<Artcletype> typelist = as.getArtType();
		System.out.println(JSON.toJSONString(typelist));
		response.getWriter().println(JSON.toJSONString(typelist));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
