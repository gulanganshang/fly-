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
import com.neuedu.service.CommentinfoService;
import com.neuedu.service.CommentinfoServiceImpl;

@WebServlet("/article/remark")
public class ArticleRemarkServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentinfoService cs=new CommentinfoServiceImpl();
		List<Map<String,Object>> remark_map=cs.chackRemark();
		System.out.println(JSON.toJSONString(remark_map));
		response.getWriter().println(JSON.toJSONString(remark_map));
	}

}
