package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Userinfo;
import com.neuedu.service.ArticleinfoService;
import com.neuedu.service.ArticleinfoServiceImpl;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.UserinfoServiceImpl;

/**
 * Servlet implementation class ArticleAddServlet
 */
//@WebServlet("/article/add")
public class ArticleAddServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String title=request.getParameter("title");
		 String content=request.getParameter("content");
	     String type =request.getParameter("class");
	     String paykiss =request.getParameter("experience");
	     int userid=((Userinfo)request.getSession().getAttribute("login_user")).getId();
	     Articleinfo  articleinfo=new Articleinfo();
	     articleinfo.setUserid(userid);
	     articleinfo.setTitle(title);
	     articleinfo.setContent(content);
	     articleinfo.setType(Integer.valueOf(type));
	     articleinfo.setPaykiss(Integer.valueOf(paykiss));
	     ArticleinfoService  as=new ArticleinfoServiceImpl();
	     as.addNewArticleinfo(articleinfo);
	     //更改当前登录者kissnum
	     Userinfo upuser=((Userinfo)request.getSession().getAttribute("login_user"));
	     upuser.setId(userid);
	     upuser.setKissnum(upuser.getKissnum()-Integer.valueOf(paykiss));
	     UserinfoService us=new UserinfoServiceImpl();
	     us.updateUserinfo(upuser);
	     response.sendRedirect("loadindex");
	     
	     
	}

}
