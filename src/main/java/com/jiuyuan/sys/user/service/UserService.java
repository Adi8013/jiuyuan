package com.jiuyuan.sys.user.service;

import com.jiuyuan.sys.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	@Transactional(readOnly = true)
	User selectByPrimaryKey(String pk);

	int updateByPrimaryKeySelective_tran(User record);

	int updateByPrimaryKey_tran(User record);

	List<User> findUserByUNlike(String userName);
}
