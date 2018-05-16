package com.jiuyuan.sys.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuyuan.sys.user.domain.User;
import com.jiuyuan.sys.user.mapper.UserMapper;
import com.jiuyuan.sys.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User loginUser(String userAccount) {
		User user = userMapper.loginUser(userAccount);
		if (user != null) {
			return user;
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int deleteByPks(String[] pks) {
		return userMapper.deleteByPks(pks);
	}

	@Override
	public User selectByPrimaryKey(String pk) {
		return userMapper.selectByPrimaryKey(pk);
	}

}