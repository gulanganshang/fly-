<<<<<<< HEAD
package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoService {
	int checkEmailExists(String email) throws IOException;
	int addNewUserinfo(Userinfo userinfo) throws IOException;
	Userinfo checkLoginUserinfo(Userinfo userinfo) throws IOException;
	int updateUserinfo(Userinfo userinfo) throws IOException;
	//更改图片
	int updateHead_url(Userinfo userinfo) throws IOException;
}
=======
package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoService {
	int checkEmailExists(String email) throws IOException;
	int addNewUserinfo(Userinfo userinfo) throws IOException;
	Userinfo checkLoginUserinfo(Userinfo userinfo) throws IOException;
	int updateUserinfo(Userinfo userinfo) throws IOException;
	//更改图片
	int updateHead_url(Userinfo userinfo) throws IOException;
}
>>>>>>> branch 'master' of https://github.com/gulanganshang/fly-.git
