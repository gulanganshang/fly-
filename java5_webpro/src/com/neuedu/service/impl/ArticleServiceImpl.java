package com.neuedu.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.bean.Artcletype;
import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;
import com.neuedu.mapper.ArticleMapper;
import com.neuedu.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	ArticleMapper am;
	@Override
	public List<Artcletype> getArtType() throws IOException {
		return am.getArtType();
	}
	@Override
	public int addArticle(Articleinfo al) throws IOException {
		int count = am.addArticle(al);
		return count;
	}
	@Override
	public List<Map<String, Object>> getAllArticle() throws IOException {
		return am.getAllArticle();
	}
	@Override
	public List<Map<String, Object>> getTopArticle(int num) throws IOException {
		
		return am.getTopArticle(num);
	}
	@Override
	public List<Map<String, Object>> getIndexTopTen(Page page) throws IOException {
		return am.getIndexTopTen(page);
	}
	@Override
	public int getArtTotalNum() throws IOException {
		return am.getArtTotalNum();
	}
	@Override
	public Map<String, Object> getArtDetail(int id) throws IOException {
		am.updateVisitNum(id);
		return am.getArtDetail(id);
	}
}
