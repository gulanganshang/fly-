package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Commentinfo;
import com.neuedu.service.CommentService;
import com.neuedu.service.impl.CommentServiceImpl;

/**
 * Servlet implementation class ReplyServlet
 */
//@WebServlet("/article/reply")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String artid = request.getParameter("artid");
		String content = request.getParameter("content");
		Commentinfo comm = new Commentinfo();
		comm.setUserid(Integer.valueOf(userid));
		comm.setArtorcommid(Integer.valueOf(artid));
		comm.setContent(content);
		// 调service存入数据库
		CommentService cs = new CommentServiceImpl();
		cs.addComment(comm);
		response.getWriter().println("1");
	}

}
