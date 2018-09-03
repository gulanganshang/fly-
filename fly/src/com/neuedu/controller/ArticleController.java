<<<<<<< HEAD
package com.neuedu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.neuedu.bean.Artcletype;
import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Commentinfo;
import com.neuedu.bean.Page;
import com.neuedu.bean.PageInfo;
import com.neuedu.bean.Userinfo;
import com.neuedu.service.ArtcletypeService;

import com.neuedu.service.ArticleinfoService;

import com.neuedu.service.CommentinfoService;

import com.neuedu.service.UserinfoService;


@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleinfoService  acs;
	@Autowired
	CommentinfoService cs;
	@Autowired
	ArtcletypeService at;
	@Autowired
	UserinfoService us;
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
	     acs.addNewArticleinfo(articleinfo);
	     //更改当前登录者kissnum
	     Userinfo upuser=((Userinfo)request.getSession().getAttribute("login_user"));
	     upuser.setId(userid);
	     upuser.setKissnum(upuser.getKissnum()-Integer.valueOf(paykiss));
	    /* UserinfoService us=new UserinfoServiceImpl();*/
	     us.updateUserinfo(upuser);
	     //response.sendRedirect("loadindex");
		 return "redirect:/article/loadindex";
	}
	@RequestMapping("/loadindex")
	public String loadArticleServlet(HttpServletRequest request) throws IOException {
		List<Map<String, Object>> indexlist=acs.checkIndexLoad();
		List<Map<String, Object>> toplist=acs.checkTopArt();
		request.setAttribute("indexlist", indexlist);
		request.setAttribute("toplist", toplist);
	    //request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		return "index";
		
	}
	@RequestMapping("/detail/{artid}")
	public String articleDetail(@PathVariable("artid") Integer artid,HttpServletRequest request) throws IOException {
		 //String artid=request.getParameter("artid");
		  Map<String, Object> art_map =acs.getIndexArt(artid);
		  //System.out.println(art_map);
		  request.setAttribute("art_map", art_map);
		  request.setAttribute("artid", artid);
		  //request.getRequestDispatcher("/jsp/jie/detail.jsp").forward(request, response);
		return "jie/detail";
	}
	//热议贴
	@RequestMapping(value="/host",method=RequestMethod.POST)
	public void articleHost(HttpServletResponse response) throws IOException {
		List<Map<String, Object>> host_map=acs.checkHostArt();
		System.out.println(JSON.toJSONString(host_map));
		response.getWriter().println(JSON.toJSONString(host_map));
	}
	@RequestMapping("/more")
	public void articleMore(Integer pageSize,Integer pageIndex,HttpServletResponse response) throws IOException {
		//String pageSizeStr=request.getParameter("pageSize");
		//String pageIndexStr=request.getParameter("pageIndex");
		
		//int pageSize=Integer.parseInt(pageSizeStr);
		//int pageIndex=Integer.parseInt(pageIndexStr);
		
		Page page=new Page();
		page.setPageSize(pageSize);
		page.setStartwith(pageIndex*pageSize);
		
		//ArticleinfoService as=new ArticleinfoServiceImpl();
		List<Map<String,Object>> list=acs.getAllArt(page);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(Map<String,Object> map:list) {
			for(Entry<String, Object> entry:map.entrySet()) {
				if(entry.getKey().equals("releasetime")) {
					//System.out.println(entry.getValue());
					entry.setValue(sdf.format(entry.getValue()));
				}
			}
		}
		int total=acs.getCount();
		
		PageInfo pageinfo=new PageInfo();
		pageinfo.setList(list);
		pageinfo.setTotal(total);
		//System.out.println(JSON.toJSONString(pageinfo));
		response.getWriter().println(JSON.toJSONString(pageinfo));
	}
	//帖子评论
	@RequestMapping("/remark")
	public void articleRemark(HttpServletResponse response) throws IOException {
		List<Map<String,Object>> remark_map=cs.chackRemark();
		System.out.println(JSON.toJSONString(remark_map));
		response.getWriter().println(JSON.toJSONString(remark_map));
	}
	//查询回复
	@RequestMapping(value="/reply",method=RequestMethod.GET)
	public void articleReply(Integer artid,HttpServletResponse response) throws IOException {
		//String artid=request.getParameter("artid");
		//CommentinfoService cs=new CommentinfoServiceImpl();
		List<Map<String,Object>> reply=cs.chackReply(artid);
		response.getWriter().println(JSON.toJSONString(reply));
	}
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public void articleReply2(Integer userid,Integer artid,String content,Commentinfo comm,HttpServletResponse response) throws IOException {	
		//String content=request.getParameter("content");
		//Commentinfo comm=new Commentinfo(); 
		
		comm.setUserid(userid);
		comm.setArtorcommid(artid);
		comm.setContent(content);
		
		//CommentinfoService cs=new CommentinfoServiceImpl();
		int count=cs.addComment(comm);
		//System.out.println(arid);
		//System.out.println(count);
		//response.getWriter().println(count);
	}
	@RequestMapping("/type")
	public void loadArticle(HttpServletResponse response) throws IOException {
		 List<Artcletype> art_list= at.checkArtcletype();
		 //System.out.println(JSON.toJSONString(art_list));
		 response.getWriter().println(JSON.toJSONString(art_list));
	}
	
}
=======
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
>>>>>>> branch 'master' of https://github.com/gulanganshang/fly-.git
