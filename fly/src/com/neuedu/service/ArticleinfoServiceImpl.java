package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;
import com.neuedu.mapper.ArticleinfoMapper;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.util.SqlSessionFactoryUtil;

public class ArticleinfoServiceImpl implements ArticleinfoService{

	@Override
	public int addNewArticleinfo(Articleinfo articleinfo) throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		int count=um.addNewArticleinfo(articleinfo);
		sqlSession.commit();
		return count;
	}

	@Override
	public List<Map<String, Object>> checkIndexLoad() throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		return um.checkIndexLoad();
	}

	@Override
	public List<Map<String, Object>> checkTopArt() throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		return um.checkTopArt();
	}

	@Override
	public List<Map<String, Object>> getAllArt(Page page) throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		return um.getAllArt(page);
	}

	@Override
	public int getCount() throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		return um.getCount();
	}

	@Override
	public Map<String, Object> getIndexArt(int id) throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		um.updateVisitnum(id);
		sqlSession.commit();
		return um.getIndexArt(id);
	}

	@Override
	public List<Map<String, Object>> checkHostArt() throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArticleinfoMapper um=sqlSession.getMapper(ArticleinfoMapper.class);
		return um.checkHostArt();
	}
}
