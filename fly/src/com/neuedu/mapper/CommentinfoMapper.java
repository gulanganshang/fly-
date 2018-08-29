package com.neuedu.mapper;

import java.util.List;
import java.util.Map;

import com.neuedu.bean.Commentinfo;

public interface CommentinfoMapper {
	int addComment(Commentinfo commentinfo);
	int updateCommNum(Commentinfo commentinfo);
	
	//查询最近回答榜
	List<Map<String,Object>> chackRemark();
	//查询回复
	List<Map<String,Object>> chackReply(int artorcommid);
}
