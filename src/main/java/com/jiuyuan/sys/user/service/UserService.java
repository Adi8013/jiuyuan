package com.jiuyuan.sys.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jiuyuan.sys.user.domain.User;

@Transactional()
public interface UserService {
	// 登录
	@Transactional(readOnly = true)
	User loginUser(String userAccount);

	@Transactional(readOnly = true)
	List<User> findAll();

	int insertSelective_tran(User record);

	int insert_tran(User record);

	// 根据主键批量删除用户
	int deleteByPks(String[] pks);

	User selectByPrimaryKey(String pk);

	int updateByPrimaryKeySelective_tran(User record);

	int updateByPrimaryKey_tran(User record);
}
