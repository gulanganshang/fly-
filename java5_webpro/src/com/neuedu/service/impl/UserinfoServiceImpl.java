package com.neuedu.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.bean.Userinfo;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.service.UserinfoService;
@Service
public class UserinfoServiceImpl implements UserinfoService{
	@Autowired
	UserinfoMapper um;
	@Override
	public int checkEmailExists(String email) throws IOException {
		int count = um.checkEmailExists(email);
		return count;
	}

	@Override
	public int addNewUserinfo(Userinfo userinfo) throws IOException {
		int count = um.addNewUserinfo(userinfo);
		return count;
	}

	@Override
	public Userinfo checkLoginUser(Userinfo userinfo) throws IOException {
		return um.checkLoginUser(userinfo);
	}

	@Override
	public int updateUserKiss(Userinfo userinfo) throws IOException {
		int count = um.updateUserKiss(userinfo);
		return count;
	}

	@Override
	public int updateHeadUrl(Userinfo userinfo) throws IOException {
		int num = um.updateHeadUrl(userinfo);
		return num;
	}

}
