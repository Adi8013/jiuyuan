package com.jiuyuan.module.sale.mapper;

import com.jiuyuan.module.sale.domain.SaleManagement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleManagementMapper {
    int deleteByPrimaryKey(String pk);

    int insert(SaleManagement record);

    int insertSelective(SaleManagement record);

    SaleManagement selectByPrimaryKey(String pk);

    int updateByPrimaryKeySelective(SaleManagement record);

    int updateByPrimaryKey(SaleManagement record);
    
    List<SaleManagement> findAll();
}