package com.neuedu.mapper;

import java.util.List;
import java.util.Map;

import com.neuedu.bean.Commentinfo;

public interface CommentinfoMapper {
	int addComment(Commentinfo commentinfo);
	int updateCommNum(Commentinfo commentinfo);
	
	//��ѯ����ش��
	List<Map<String,Object>> chackRemark();
	//��ѯ�ظ�
	List<Map<String,Object>> chackReply(int artorcommid);
}
