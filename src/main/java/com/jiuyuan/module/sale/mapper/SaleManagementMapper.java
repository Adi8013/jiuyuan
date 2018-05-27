package com.jiuyuan.module.sale.mapper;

import java.util.List;

import com.jiuyuan.module.sale.domain.SaleManagement;

public interface SaleManagementMapper {
    int deleteByPrimaryKey(String pk);

    int insert(SaleManagement record);

    int insertSelective(SaleManagement record);

    SaleManagement selectByPrimaryKey(String pk);

    int updateByPrimaryKeySelective(SaleManagement record);

    int updateByPrimaryKey(SaleManagement record);
    
    List<SaleManagement> findAll();
    
 // 根据主键批量删除用户
 	int deleteByPks(String[] pks);
}