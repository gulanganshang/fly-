package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.neuedu.bean.Commentinfo;

public interface CommentinfoService {
	int addComment(Commentinfo commentinfo) throws IOException;
	List<Map<String,Object>> chackRemark() throws IOException;
	
	List<Map<String,Object>> chackReply(int artorcommid) throws IOException;
}
