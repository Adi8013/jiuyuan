package com.jiuyuan.sys.user.mapper;

import com.jiuyuan.sys.user.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String pk);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String pk);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    // 根据用户名(用户名注册的时候保证唯一性)
    User loginUser(String userAccount);
    
}