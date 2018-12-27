package com.jiuyuan.module.common.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository()
public interface CommonMapper {
    // 查询最大业务编号
    String seleceMaxBussCode(Map<String, String> map);
}
