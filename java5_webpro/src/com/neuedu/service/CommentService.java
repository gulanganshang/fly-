package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Commentinfo;

public interface CommentService {

	// 添加一条评论
	int addComment(Commentinfo comm) throws IOException;
}
