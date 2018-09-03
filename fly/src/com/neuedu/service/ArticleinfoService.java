package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;

public interface ArticleinfoService {
	int addNewArticleinfo(Articleinfo articleinfo) throws IOException;
	List<Map<String,Object>> checkIndexLoad() throws IOException;
	List<Map<String,Object>> checkTopArt() throws IOException;
	
    List<Map<String,Object>> getAllArt(Page page) throws IOException;
	
	int getCount() throws IOException;
	
	Map<String,Object> getIndexArt(int id) throws IOException;
	//更改访问量
	List<Map<String,Object>> checkHostArt() throws IOException;
}
