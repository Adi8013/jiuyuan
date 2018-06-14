package com.jiuyuan.module.sale.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiuyuan.module.sale.domain.SaleDetail;

@Repository
public interface SaleDetailMapper {
    int insert(SaleDetail record);

    int insertSelective(SaleDetail record);
    
    List<SaleDetail> queryDetailBySalePk(String salePk);
    
    int updateByPrimaryKeySelective(SaleDetail record);

    int updateByPrimaryKey(SaleDetail record);
    
    int deleteByPrimaryKey(String pk);
    
}