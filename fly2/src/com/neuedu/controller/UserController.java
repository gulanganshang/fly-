package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.bean.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.util.MD5;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserinfoService us;

	@RequestMapping("/headupload")
	public void headUpload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		// ��ȡ�ϴ�·��
		String uploadpath = request.getServletContext().getRealPath("/headphoto/");
		File files = new File(uploadpath);
		// ���ϴ�·�������ڣ��½�·��
		if (!files.exists()) {
			files.mkdirs();
		}
		// �ϴ��ļ����յ�·��
		String newName = UUID.randomUUID() + fileName;
		String finalName = uploadpath + newName;
		File finalfile = new File(finalName);
		// �ϴ���ָ��·��
		file.transferTo(finalfile);
		// 1.��ǰ̨ҳ���ͷ��·��
		response.getWriter().println(request.getContextPath() + "/headphoto/" + newName);
		// 2.��session��login_user��ͷ�����һ��
		Userinfo userinfo = (Userinfo) request.getSession().getAttribute("login_user");
		userinfo.setHead_url(newName);
		// 3.�޸�userinfo���¼����Ϣ���е�head_url
		us.updateHead_url(userinfo);
		/*UserinfoService us = new UserinfoServiceImpl();
		us.updateHead_url(userinfo);*/
	}

	@RequestMapping("/login")
	public String login(Userinfo userinfo, String email, String pass, HttpSession session) throws IOException {
		// String email = request.getParameter("email");
		// String password = request.getParameter("pass");
		userinfo.setEmail(email);
		userinfo.setPassword(MD5.MD5(pass));

		Userinfo user = us.checkLoginUserinfo(userinfo);
		if (user != null) {
			// request.getSession().setAttribute("login_user", user);
			session.setAttribute("login_user", user);
			// response.sendRedirect(request.getContextPath()+"/article/loadindex");
			return "redirect:/article/loadindex";
		} else {
			// request.getSession().setAttribute("msg", "�û������������");
			session.setAttribute("msg", "�û������������");
			// request.getRequestDispatcher("/jsp/user/login.jsp").forward(request,
			// response);
			return "user/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("login_user");
		// response.sendRedirect(request.getContextPath()+ "/article/loadindex");
		return "redirect:/article/loadindex";

	}

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public void reg(String email, HttpServletResponse response) throws IOException {
		// String email = request.getParameter("email");
		System.out.println(email);
		int count = us.checkEmailExists(email);
		response.getWriter().println(count);
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String reg(String nickname, String email, String password, Userinfo userinfo, HttpSession session)
			throws IOException {
		/*
		 * String nickname = request.getParameter("nickname"); String email =
		 * request.getParameter("email"); String password =
		 * request.getParameter("password"); Userinfo userinfo = new Userinfo();
		 */
		userinfo.setNickname(nickname);
		userinfo.setEmail(email);
		userinfo.setPassword(MD5.MD5(password));
		int cou = us.addNewUserinfo(userinfo);
		if (cou == 1) {
			session.setAttribute("reg_email", email);
			// response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
			return "user/login";
		} else {
			// response.sendRedirect(request.getContextPath()+"/jsp/user/reg.jsp");
			return "user/reg";
		}
	}
}
