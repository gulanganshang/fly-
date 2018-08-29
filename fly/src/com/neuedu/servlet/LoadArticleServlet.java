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
import com.neuedu.bean.Artcletype;
import com.neuedu.service.ArtcletypeService;
import com.neuedu.service.ArtcletypeServiceImpl;

import javafx.print.JobSettings;

@WebServlet("/article/type")
public class LoadArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ArtcletypeService as=new ArtcletypeServiceImpl();
		 List<Artcletype> art_list= as.checkArtcletype();
		 System.out.println(JSON.toJSONString(art_list));
		 response.getWriter().println(JSON.toJSONString(art_list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
