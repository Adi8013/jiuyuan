package com.jiuyuan.module.sale.serviec.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.mapper.SaleDetailMapper;
import com.jiuyuan.module.sale.mapper.SaleManagementMapper;
import com.jiuyuan.module.sale.serviec.SaleManagementService;
import com.jiuyuan.utils.NumToCHUtil;

@Service
public class SaleManagementServiceImpl implements SaleManagementService {

	@Autowired
	private SaleManagementMapper saleManagementService;
	@Autowired
	private SaleDetailMapper saleDetailMapper;
	
	
	@Override
	public List<SaleManagement> findAll() {
		return saleManagementService.findAll();
	}

	@Override
	public int insertSelective_tran(SaleManagement record) {
		return saleManagementService.insertSelective(record);
	}

	@Override
	public int insert_tran(SaleManagement record) {
		return saleManagementService.insert(record);
	}

	@Override
	public SaleManagement selectByPrimaryKey(String pk) {
		
		SaleManagement sale = saleManagementService.selectByPrimaryKey(pk);
		
		// 设置销售单总金额
		sale.setTotalPrice(saleDetailMapper.querySumPriceBySaleNo(sale.getSaleNo()).doubleValue());
		// 设置人民币大写
		sale.setRmbCH(NumToCHUtil.numToCN(new BigDecimal(Double.toString(sale.getTotalPrice()))));
		return sale;
	}

	@Override
	public int updateByPrimaryKeySelective_tran(SaleManagement record) {
		return saleManagementService.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey_tran(SaleManagement record) {
		return saleManagementService.updateByPrimaryKey(record);
	}

}
