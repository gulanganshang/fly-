package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.bean.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.impl.UserinfoServiceImpl;
import com.neuedu.util.MD5;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserinfoService us;

	@RequestMapping("/checkreg")
	public void checkReg(String email, HttpServletResponse response) throws IOException {
		// 调用service查询数据中是否有这个邮箱(servlet-->mapper servlet-->service-->mapper )
		int count = us.checkEmailExists(email);
		response.getWriter().println(count);
	}

	@RequestMapping("/reg")
	public String reg(Userinfo userinfo) throws IOException {
		// 密码加密
		userinfo.setPassword(MD5.MD5(userinfo.getPassword()));
		// 调用对应service，完成插入新用户
		int count = us.addNewUserinfo(userinfo);
		if (count == 1) {
			return "redirect:/article/loadindex";
		} else {
			return "redirect:/page/loadreg";
		}
	}

	@RequestMapping("/login")
	public String dologin(Userinfo userinfo, HttpServletRequest request) throws IOException {
		userinfo.setPassword(MD5.MD5(userinfo.getPassword()));
		// 调service查询是否可以登录
		Userinfo user = us.checkLoginUser(userinfo);
		if (user != null) {
			// 可以登录
			request.getSession().setAttribute("login_user", user);
			return "redirect:/article/loadindex";
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			return "user/login";
		}
	}

	@RequestMapping("/headupload")
	public void uploadHead(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取上传路径
		String uploadpath = request.getServletContext().getRealPath("/upload/");
		File files = new File(uploadpath);
		// 若上传路径不存在，新建路径
		if (!files.exists()) {
			files.mkdirs();
		}
		
		// 上传文件最终的路径
		String newName = UUID.randomUUID() + fileName;
		String finalName = uploadpath + newName;
		File finalfile = new File(finalName);
		// 上传到指定路径
		file.transferTo(finalfile);
		// 1.给前台页面的头像路径
		response.getWriter().println(request.getContextPath()+"/upload/"+newName);
		// 2.把session中login_user的头像更新一下
		Userinfo userinfo = (Userinfo) request.getSession().getAttribute("login_user");
		userinfo.setHead_url(newName);
		// 3.修改userinfo表登录者信息当中的head_url
		us.updateHeadUrl(userinfo);
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/article/loadindex";
	}
}
