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
import com.neuedu.bean.Page;
import com.neuedu.bean.PageInfo;
import com.neuedu.service.ArticleService;
import com.neuedu.service.impl.ArticleServiceImpl;

//@WebServlet("/article/pageindex")
public class PageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageSizeStr = request.getParameter("pageSize");
		String pageIndexStr = request.getParameter("pageIndex");
		int pageSize = Integer.valueOf(pageSizeStr);
		int pageIndex = Integer.valueOf(pageIndexStr);
		// 调service
		ArticleService as = new ArticleServiceImpl();
		Page page = new Page();
		page.setPageSize(pageSize);
		page.setStartwith(pageSize*pageIndex);
		// 首页10个文章
		List<Map<String, Object>> list = as.getIndexTopTen(page);
		// ------------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Map<String, Object> map:list) {
			for(Entry<String, Object> entry:map.entrySet()) {
				System.out.println(entry.getKey()+"  "+entry.getValue());
				if(entry.getKey().equals("releasetime")) {
					entry.setValue(sdf.format(entry.getValue()));
				}
			}
		}
		// ------------------------------------------------------
		// 获取文章总数
		int count = as.getArtTotalNum();
		PageInfo pageinfo = new PageInfo();
		pageinfo.setList(list);
		pageinfo.setTotal(count);
		response.getWriter().println(JSON.toJSONString(pageinfo));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
