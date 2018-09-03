package com.neuedu.service.impl;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.bean.Userinfo;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.service.UserinfoService;
import com.neuedu.util.SqlSessionFactoryUtil;
@Service
public class UserinfoServiceImpl implements UserinfoService{
	@Autowired
	UserinfoMapper um;
	@Override
	public int checkEmailExists(String email) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		UserinfoMapper um=sqlSession.getMapper(UserinfoMapper.class);*/
		int count=um.checkEmailExists(email);
		/*System.out.println(count);*/
		return count;
	}

	@Override
	public int addNewUserinfo(Userinfo userinfo) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		UserinfoMapper um=sqlSession.getMapper(UserinfoMapper.class);*/
		int count=um.addNewUserinfo(userinfo);
		/*sqlSession.commit();
		System.out.println(count);*/
		return count;
	}

	@Override
	public Userinfo checkLoginUserinfo(Userinfo userinfo) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		UserinfoMapper um=sqlSession.getMapper(UserinfoMapper.class);*/
		Userinfo user= um.checkLoginUserinfo(userinfo);
		return user;
	}

	@Override
	public int updateUserinfo(Userinfo userinfo) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		UserinfoMapper um=sqlSession.getMapper(UserinfoMapper.class);*/
		int count=um.updateUserinfo(userinfo);
		/*sqlSession.commit();*/
		return count;
	}

	@Override
	public int updateHead_url(Userinfo userinfo) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		UserinfoMapper um=sqlSession.getMapper(UserinfoMapper.class);*/
		int count=um.updateHead_url(userinfo);
		/*sqlSession.commit();*/
		return count;
	}
}
