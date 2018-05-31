package com.jiuyuan.module.sale.mapper;

import com.jiuyuan.module.sale.domain.SaleDetail;

public interface SaleDetailMapper {
    int insert(SaleDetail record);

    int insertSelective(SaleDetail record);
}