package com.neuedu.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {

	private static SqlSessionFactoryUtil sqlSessionFactoryUtil;

	private SqlSessionFactoryUtil() {
	}

	public static SqlSessionFactoryUtil getInstance() {
		if (sqlSessionFactoryUtil == null) {
			sqlSessionFactoryUtil = new SqlSessionFactoryUtil();
		}
		return sqlSessionFactoryUtil;
	}

	public SqlSession getSqlSession() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
