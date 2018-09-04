package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Userinfo;
import com.neuedu.service.ArticleService;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.impl.ArticleServiceImpl;
import com.neuedu.service.impl.UserinfoServiceImpl;

/**
 * Servlet implementation class AddArticleServlet
 */
//@WebServlet("/article/add")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object user = request.getSession().getAttribute("login_user");
		if(user == null) {
			// 没登录先登录
			response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
		}else {
			// 跳转到发文章的jsp
			response.sendRedirect(request.getContextPath()+"/jsp/jie/add.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文章标题
		String title = request.getParameter("title");
		// 文章内容
		String content = request.getParameter("content");
		// 文章类型
		String type = request.getParameter("class");
		// 消耗的飞吻
		String paykiss = request.getParameter("experience");
		// 发布者id
		int userid = ((Userinfo)request.getSession().getAttribute("login_user")).getId();
		// 封装成对象
		Articleinfo al = new Articleinfo();
		al.setTitle(title);
//		al.setContent(content);
		al.setType(Integer.valueOf(type));
		al.setPaykiss(Integer.valueOf(paykiss));
		al.setUserid(userid);
		// 更新当前登录者kissnum
		Userinfo user = ((Userinfo)request.getSession().getAttribute("login_user"));
		user.setKissnum(user.getKissnum()-Integer.valueOf(paykiss));
		// 调service:1.在文章表中增加一篇文章；2.更新了userinfo的飞吻数
		ArticleService as = new ArticleServiceImpl();
		int count = as.addArticle(al);
		UserinfoService us = new UserinfoServiceImpl();
		us.updateUserKiss(user);
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
	}

}
