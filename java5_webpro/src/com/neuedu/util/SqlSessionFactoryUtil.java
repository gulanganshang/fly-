package com.neuedu.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	private static SqlSessionFactoryUtil ssf;
	private SqlSessionFactoryUtil() {}
	public static SqlSessionFactoryUtil getInstance() {
		if (ssf == null) {
			ssf = new SqlSessionFactoryUtil();
		}
		return ssf;
	}
	public SqlSession getSqlSession() throws IOException {
		// 调mapper
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
