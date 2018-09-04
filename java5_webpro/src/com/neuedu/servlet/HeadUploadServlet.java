package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class HeadUploadServlet
 */
//@WebServlet("/user/headupload")
//@MultipartConfig
public class HeadUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String savePath = request.getServletContext().getRealPath("/upload");
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		PrintWriter out = response.getWriter();
		// 获取上传的文件集合
		Collection<Part> parts = request.getParts();	
			for(Part part:parts) {
				if(part.getContentType() != null) {
					String header = part.getHeader("content-disposition");
					// 获取文件名
					String fileName = getFileName(header);
					// 存储
					// 时间戳解决文件重名
//					Date date = new Date();
					UUID uuid = UUID.randomUUID();
					part.write(savePath + File.separator + uuid+fileName);
					System.out.println(fileName);
					// 1.给前台页面的头像路径
					out.println(request.getContextPath()+"/upload/"+uuid+fileName);
					// 2.把session中login_user的头像更新一下
					// 3.修改userinfo表登录者信息当中的head_url
				}
			}
		out.flush();
		out.close();
	}
	public String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		for(String str:tempArr1) {
			System.out.println(str);
		}
		/**
		 * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// 获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}
