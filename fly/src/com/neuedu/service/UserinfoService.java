package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoService {
	int checkEmailExists(String email) throws IOException;
	int addNewUserinfo(Userinfo userinfo) throws IOException;
	Userinfo checkLoginUserinfo(Userinfo userinfo) throws IOException;
	int updateUserinfo(Userinfo userinfo) throws IOException;
	//¸ü¸ÄÍ¼Æ¬
	int updateHead_url(Userinfo userinfo) throws IOException;
}
