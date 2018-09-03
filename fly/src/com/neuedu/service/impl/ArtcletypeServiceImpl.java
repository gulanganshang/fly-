package com.neuedu.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.bean.Artcletype;
import com.neuedu.mapper.ArtcletypeMapper;
import com.neuedu.mapper.UserinfoMapper;
import com.neuedu.service.ArtcletypeService;
import com.neuedu.util.SqlSessionFactoryUtil;
@Service
public class ArtcletypeServiceImpl  implements ArtcletypeService{
	@Autowired
	ArtcletypeMapper at;
	@Override
	public List<Artcletype> checkArtcletype() throws IOException {
		/*SqlSession sqlSession=SqlSessionFactoryUtil.getInstance().getSqlSession();
		ArtcletypeMapper at=sqlSession.getMapper(ArtcletypeMapper.class);*/
		return at.checkArtcletype();
	}


}
