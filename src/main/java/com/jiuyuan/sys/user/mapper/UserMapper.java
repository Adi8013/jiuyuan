package com.jiuyuan.sys.user.mapper;

import com.jiuyuan.sys.user.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String pk);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String pk);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}