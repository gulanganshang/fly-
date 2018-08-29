package com.neuedu.mapper;

import java.util.List;
import java.util.Map;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;
import com.neuedu.bean.Userinfo;

public interface ArticleinfoMapper {
	int addNewArticleinfo(Articleinfo articleinfo);
	List<Map<String,Object>> checkIndexLoad();
	List<Map<String,Object>> checkTopArt();
	
	List<Map<String,Object>> getAllArt(Page page);
	
	int getCount();
	
	//查询当前所点击的的帖子信息
	Map<String,Object> getIndexArt(int id);
	//更改访问量
	int updateVisitnum(int id);
	
	//查询当前热议贴
	List<Map<String,Object>> checkHostArt();
}
