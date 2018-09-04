package com.neuedu.mapper;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoMapper {
	// 查询邮箱是否存在
	int checkEmailExists(String email);
	// 添加新用户
	int addNewUserinfo(Userinfo userinfo);
	// 查询登录用户是否存在
	Userinfo checkLoginUser(Userinfo userinfo);
	// 更新飞吻数
	int updateUserKiss(Userinfo userinfo);
	// 更新头像
	int updateHeadUrl(Userinfo userinfo) throws IOException;
}
