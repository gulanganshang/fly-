package com.neuedu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.neuedu.service.ArticleService;
import com.neuedu.service.impl.ArticleServiceImpl;

/**
 * Servlet implementation class LoadIndexServlet
 */
//@WebServlet("/article/loadindex")
public class LoadIndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查询所有帖子
		ArticleService as = new ArticleServiceImpl();
		List<Map<String, Object>> artlist = as.getAllArticle();
		request.setAttribute("artlist", artlist);
		List<Map<String, Object>> toplist = as.getTopArticle(2);
		request.setAttribute("toplist", toplist);
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		//============================================================不推荐
/*		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Map<String, Object> map:artlist) {
			for(Entry<String, Object> entry:map.entrySet()) {
				System.out.println(entry.getKey()+"  "+entry.getValue());
				if(entry.getKey().equals("releasetime")) {
					entry.setValue(sdf.format(entry.getValue()));
				}
			}
		}
		if(artlist != null && artlist.size() != 0) {
			System.out.println(JSON.toJSONString(artlist));
			response.getWriter().println(JSON.toJSONString(artlist));
		}*/
		//===============================================================
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
