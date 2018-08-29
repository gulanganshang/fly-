package com.neuedu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Userinfo;
import com.neuedu.service.ArticleinfoService;
import com.neuedu.service.ArticleinfoServiceImpl;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.UserinfoServiceImpl;

@Controller
@RequestMapping("/article")
public class ArticleController {
	ArticleinfoService  as=new ArticleinfoServiceImpl();
	@RequestMapping("/add")
	public String articleAdd(HttpServletRequest request,Articleinfo  articleinfo) throws IOException {
		 String title=request.getParameter("title");
		 String content=request.getParameter("content");
	     String type =request.getParameter("class");
	     String paykiss =request.getParameter("experience");
	     int userid=((Userinfo)request.getSession().getAttribute("login_user")).getId();
	    // Articleinfo  articleinfo=new Articleinfo();
	     articleinfo.setUserid(userid);
	     articleinfo.setTitle(title);
	     articleinfo.setContent(content);
	     articleinfo.setType(Integer.valueOf(type));
	     articleinfo.setPaykiss(Integer.valueOf(paykiss));
	     as.addNewArticleinfo(articleinfo);
	     //更改当前登录者kissnum
	     Userinfo upuser=((Userinfo)request.getSession().getAttribute("login_user"));
	     upuser.setId(userid);
	     upuser.setKissnum(upuser.getKissnum()-Integer.valueOf(paykiss));
	     UserinfoService us=new UserinfoServiceImpl();
	     us.updateUserinfo(upuser);
	     //response.sendRedirect("loadindex");
		 return "redirect:/article/loadindex";
	}
	@RequestMapping("/loadindex")
	public String loadArticleServlet(HttpServletRequest request) throws IOException {
		List<Map<String, Object>> indexlist=as.checkIndexLoad();
		List<Map<String, Object>> toplist=as.checkTopArt();
		request.setAttribute("indexlist", indexlist);
		request.setAttribute("toplist", toplist);
	    //request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		return "index";
		
	}
}
