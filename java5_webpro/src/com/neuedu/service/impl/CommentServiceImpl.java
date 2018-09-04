package com.neuedu.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.bean.Commentinfo;
import com.neuedu.mapper.CommentMapper;
import com.neuedu.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentMapper cm;
	@Override
	@Transactional
	public int addComment(Commentinfo comm) throws IOException {
		int num1 = cm.addComment(comm);
		int num2 = cm.updateCommNum(comm);
		return num1;
	}

}
