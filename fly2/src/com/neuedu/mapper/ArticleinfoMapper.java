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
	
	//��ѯ��ǰ������ĵ�������Ϣ
	Map<String,Object> getIndexArt(int id);
	//���ķ�����
	int updateVisitnum(int id);
	
	//��ѯ��ǰ������
	List<Map<String,Object>> checkHostArt();
}
