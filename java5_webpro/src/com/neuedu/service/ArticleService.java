package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.neuedu.bean.Artcletype;
import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;

public interface ArticleService {
	// 查找所有帖子分类
	List<Artcletype> getArtType() throws IOException;
	// 添加文章
	int addArticle(Articleinfo al) throws IOException;
	// 查询首页10篇文章
	List<Map<String, Object>> getAllArticle() throws IOException;
	// 查询置顶或加精文章4个
	List<Map<String, Object>> getTopArticle(int num) throws IOException;
	// 查询首页的10个文章子
	List<Map<String, Object>> getIndexTopTen(Page page) throws IOException;
	// 查询文章总数
	int getArtTotalNum() throws IOException;
	// 查看谋篇文章
	Map<String, Object> getArtDetail(int id) throws IOException;
}
