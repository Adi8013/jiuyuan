package com.jiuyuan.sys.user.mapper;

import com.jiuyuan.sys.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    
	int deleteByPrimaryKey(String pk);
    
    // 根据主键批量删除用户
	int deleteByPks(String[] pks);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(String pk);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
    // 根据用户名(用户名注册的时候保证唯一性)
    User loginUser(String userAccount);
    
    List<User> findAll();

    List<User> findUserByUNlike(String userName);
    
}