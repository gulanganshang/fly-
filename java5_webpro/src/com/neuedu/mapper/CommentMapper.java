package com.neuedu.mapper;

import com.neuedu.bean.Commentinfo;

public interface CommentMapper {
	// 添加一条评论
	int addComment(Commentinfo comm);
	// 更新对应文章的评论数
	int updateCommNum(Commentinfo comm);
}
