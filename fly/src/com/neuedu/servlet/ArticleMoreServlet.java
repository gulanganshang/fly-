package com.neuedu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.neuedu.service.ArticleinfoService;
import com.neuedu.service.ArticleinfoServiceImpl;

@WebServlet("/article/more")
public class ArticleMoreServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String pageSizeStr=request.getParameter("pageSize");
	String pageIndexStr=request.getParameter("pageIndex");
	
	int pageSize=Integer.parseInt(pageSizeStr);
	int pageIndex=Integer.parseInt(pageIndexStr);
	
	Page page=new Page();
	page.setPageSize(pageSize);
	page.setStartwith(pageIndex*pageSize);
	
	ArticleinfoService as=new ArticleinfoServiceImpl();
	List<Map<String,Object>> list=as.getAllArt(page);
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	for(Map<String,Object> map:list) {
		for(Entry<String, Object> entry:map.entrySet()) {
			if(entry.getKey().equals("releasetime")) {
				//System.out.println(entry.getValue());
				entry.setValue(sdf.format(entry.getValue()));
			}
		}
	}
	int total=as.getCount();
	
	PageInfo pageinfo=new PageInfo();
	pageinfo.setList(list);
	pageinfo.setTotal(total);
	//System.out.println(JSON.toJSONString(pageinfo));
	response.getWriter().println(JSON.toJSONString(pageinfo));
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
