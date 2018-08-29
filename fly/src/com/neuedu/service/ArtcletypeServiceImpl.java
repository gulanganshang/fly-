package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.bean.Artcletype;
import com.neuedu.mapper.ArtcletypeMapper;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.util.SqlSessionFactoryUtil;

public class ArtcletypeServiceImpl  implements ArtcletypeService{

	@Override
	public List<Artcletype> checkArtcletype() throws IOException {
		SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArtcletypeMapper at=sqlSession.getMapper(ArtcletypeMapper.class);
		return at.checkArtcletype();
	}


}
