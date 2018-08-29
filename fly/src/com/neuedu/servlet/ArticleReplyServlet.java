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
import com.neuedu.bean.Commentinfo;
import com.neuedu.service.CommentinfoService;
import com.neuedu.service.CommentinfoServiceImpl;

/**
 * Servlet implementation class ArticleReplyServlet
 */
@WebServlet("/article/reply")
public class ArticleReplyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String artid=request.getParameter("artid");
		CommentinfoService cs=new CommentinfoServiceImpl();
		List<Map<String,Object>> reply=cs.chackReply(Integer.valueOf(artid));
		System.out.println(JSON.toJSONString(artid));
		System.out.println(JSON.toJSONString(reply));
		response.getWriter().println(JSON.toJSONString(reply));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String userid=request.getParameter("userid");
	String arid=request.getParameter("artid");
	
	String content=request.getParameter("content");
	Commentinfo comm=new Commentinfo(); 
	
	comm.setUserid(Integer.valueOf(userid));
	comm.setArtorcommid(Integer.valueOf(arid));
	comm.setContent(content);
	
	CommentinfoService cs=new CommentinfoServiceImpl();
	int count=cs.addComment(comm);
	//System.out.println(arid);
	//System.out.println(count);
	response.getWriter().println(count);
	}

}
