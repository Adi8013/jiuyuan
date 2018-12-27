package com.jiuyuan.module.common.service;

import java.util.Map;

public interface CommonService {
    /**
     * 查询最大业务编号
     * @param tableName 表名
     * @param columnName 字段名
     * @param condition like 条件
     * @return 最大业务编号
     */
    String seleceMaxBussCode(String tableName, String columnName, String condition);
}
