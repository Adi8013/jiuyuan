package com.jiuyuan.sys.user.service;


import java.util.List;

import com.jiuyuan.sys.user.domain.User;

public interface UserService {
	// 登录
	User loginUser(String userAccount);
	
	List<User> findAll();
}
