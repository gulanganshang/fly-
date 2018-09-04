package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoService {
	// 查询邮箱是否存在
	int checkEmailExists(String email) throws IOException;
	// 添加新用户
	int addNewUserinfo(Userinfo userinfo) throws IOException;
	// 查询登录用户是否存在
	Userinfo checkLoginUser(Userinfo userinfo) throws IOException;
	// 更新飞吻数
	int updateUserKiss(Userinfo userinfo) throws IOException;
	// 更新头像
	int updateHeadUrl(Userinfo userinfo) throws IOException;
}
