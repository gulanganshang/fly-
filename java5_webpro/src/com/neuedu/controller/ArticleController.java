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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.neuedu.bean.Artcletype;
import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Commentinfo;
import com.neuedu.bean.Page;
import com.neuedu.bean.PageInfo;
import com.neuedu.bean.Userinfo;
import com.neuedu.service.ArticleService;
import com.neuedu.service.CommentService;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.impl.ArticleServiceImpl;
import com.neuedu.service.impl.CommentServiceImpl;
import com.neuedu.service.impl.UserinfoServiceImpl;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleService as;
	@Autowired
	UserinfoService us;
	@Autowired
	CommentService cs;
	
	@RequestMapping("/loadindex")
	public ModelAndView loadIndex() throws IOException {
		ModelAndView mav = new ModelAndView();
		// 查询所有帖子
		List<Map<String, Object>> artlist = as.getAllArticle();
		mav.addObject("artlist", artlist);
		List<Map<String, Object>> toplist = as.getTopArticle(2);
		mav.addObject("toplist", toplist);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/detail/{id}")
	public String loadDetail(@PathVariable("id") Integer id, Model model) throws IOException {
		// 调service
		// 增加访问量
		// 查询文章内容
		Map<String, Object> map = as.getArtDetail(id);
		model.addAttribute("artmap", map);
		// 评论--暂未实现
		return "jie/detail";
	}

	@RequestMapping("/add")
	public String addArt(Articleinfo al, HttpServletRequest request) throws IOException {
		int userid = ((Userinfo) request.getSession().getAttribute("login_user")).getId();
		// 封装成对象
		al.setUserid(userid);
		// 渲染的html参数
		al.setTopic_html_content(request.getParameter("test-editormd-html-code"));
		// 更新当前登录者kissnum
		Userinfo user = ((Userinfo) request.getSession().getAttribute("login_user"));
		user.setKissnum(user.getKissnum() - al.getPaykiss());
		// 调service:1.在文章表中增加一篇文章；2.更新了userinfo的飞吻数
		int count = as.addArticle(al);
		us.updateUserKiss(user);
		return "redirect:/article/loadindex";
	}

	@RequestMapping("/type")
	public void loadArtType(HttpServletResponse response) throws IOException {
		// 查询所有帖子分类
		List<Artcletype> typelist = as.getArtType();
		response.getWriter().println(JSON.toJSONString(typelist));
	}
	
	@RequestMapping("/pageindex")
	public void pageIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageSizeStr = request.getParameter("pageSize");
		String pageIndexStr = request.getParameter("pageIndex");
		int pageSize = Integer.valueOf(pageSizeStr);
		int pageIndex = Integer.valueOf(pageIndexStr);
		// 调service
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
	
	@RequestMapping("/reply")
	public void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		String artid = request.getParameter("artid");
		String content = request.getParameter("content");
		Commentinfo comm = new Commentinfo();
		comm.setUserid(Integer.valueOf(userid));
		comm.setArtorcommid(Integer.valueOf(artid));
		comm.setContent(content);
		// 调service存入数据库
		cs.addComment(comm);
		response.getWriter().println("1");
	}
}
