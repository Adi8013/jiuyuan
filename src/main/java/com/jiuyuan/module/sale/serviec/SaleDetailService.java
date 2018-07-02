package com.jiuyuan.module.sale.serviec;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jiuyuan.module.sale.domain.SaleDetail;

@Transactional()
public interface SaleDetailService {

	@Transactional(readOnly = true)
	List<SaleDetail> queryDetailBySalePk(String salePk);

	int insertSelective_tran(SaleDetail record);

	int insert_tran(SaleDetail record);

	int updateByPrimaryKeySelective_tran(SaleDetail record);

	int updateByPrimaryKey_tran(SaleDetail record);
	
	int deleteByPrimaryKey(String pk);
	
	/**
	 * 根据销售单号查询总金额
	 * @param saleNo
	 * @return
	 */
	Double querySumPriceBySaleNo(String saleNo);
	
}
