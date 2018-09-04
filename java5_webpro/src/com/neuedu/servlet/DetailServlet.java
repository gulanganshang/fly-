package com.neuedu.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.ArticleService;
import com.neuedu.service.impl.ArticleServiceImpl;

/**
 * Servlet implementation class DetailServlet
 */
//@WebServlet("/article/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String artid = request.getParameter("artid");
		// 调service
		ArticleService as = new ArticleServiceImpl();
		// 增加访问量
		// 查询文章内容
		Map<String, Object> map = as.getArtDetail(Integer.valueOf(artid));
		request.setAttribute("artmap", map);
		// 评论--暂未实现
		request.getRequestDispatcher("/jsp/jie/detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
