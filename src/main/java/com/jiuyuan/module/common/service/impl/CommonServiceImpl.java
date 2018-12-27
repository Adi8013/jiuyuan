package com.jiuyuan.module.common.service.impl;

import com.jiuyuan.module.common.mapper.CommonMapper;
import com.jiuyuan.module.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service()
public class CommonServiceImpl implements CommonService {
    @Autowired()
    private CommonMapper commonMapper;
    // 查询最大业务编号
    @Override
    public String seleceMaxBussCode(String tableName, String columnName, String condition) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("tableName", tableName);
        map.put("columnName", columnName);
        map.put("condition", condition);
        return commonMapper.seleceMaxBussCode(map);
    }



}
