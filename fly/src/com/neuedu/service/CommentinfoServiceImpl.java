package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.bean.Commentinfo;
import com.neuedu.mapper.ArticleinfoMapper;
import com.neuedu.mapper.CommentinfoMapper;
import com.neuedu.util.SqlSessionFactoryUtil;

public class CommentinfoServiceImpl implements CommentinfoService{

	@Override
	public int addComment(Commentinfo commentinfo) throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		CommentinfoMapper cm=sqlSession.getMapper(CommentinfoMapper.class);
		int count1= cm.addComment(commentinfo);
		int count2=cm.updateCommNum(commentinfo);
		sqlSession.commit();
		return count1;
	}

	@Override
	public List<Map<String, Object>> chackRemark() throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		CommentinfoMapper cm=sqlSession.getMapper(CommentinfoMapper.class);
		return cm.chackRemark();
	}

	@Override
	public List<Map<String, Object>> chackReply(int artorcommid) throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		CommentinfoMapper cm=sqlSession.getMapper(CommentinfoMapper.class);
		return cm.chackReply(artorcommid);
	}

}
