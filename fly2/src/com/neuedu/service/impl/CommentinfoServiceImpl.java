package com.neuedu.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.bean.Commentinfo;
import com.neuedu.mapper.ArticleinfoMapper;
import com.neuedu.mapper.CommentinfoMapper;
import com.neuedu.service.CommentinfoService;
import com.neuedu.util.SqlSessionFactoryUtil;
@Service
public class CommentinfoServiceImpl implements CommentinfoService{
	@Autowired
	CommentinfoMapper cm;
	@Override
	@Transactional
	public int addComment(Commentinfo commentinfo) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		CommentinfoMapper cm=sqlSession.getMapper(CommentinfoMapper.class);*/
		int count1= cm.addComment(commentinfo);
		int count2=cm.updateCommNum(commentinfo);
		/*sqlSession.commit();*/
		return count1;
	}

	@Override
	public List<Map<String, Object>> chackRemark() throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		CommentinfoMapper cm=sqlSession.getMapper(CommentinfoMapper.class);*/
		return cm.chackRemark();
	}

	@Override
	public List<Map<String, Object>> chackReply(int artorcommid) throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		CommentinfoMapper cm=sqlSession.getMapper(CommentinfoMapper.class);*/
		return cm.chackReply(artorcommid);
	}

}
