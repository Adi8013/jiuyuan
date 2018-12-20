package com.jiuyuan.module.sale.mapper;

import com.jiuyuan.module.sale.domain.SaleDetail;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SaleDetailMapper {
    int insert(SaleDetail record);

    int insertSelective(SaleDetail record);
    
    List<SaleDetail> queryDetailBySalePk(String salePk);
    
    int updateByPrimaryKeySelective(SaleDetail record);

    int updateByPrimaryKey(SaleDetail record);
    
    int deleteByPrimaryKey(String pk);

	BigDecimal querySumPriceBySaleNo(String saleNo);
    
}