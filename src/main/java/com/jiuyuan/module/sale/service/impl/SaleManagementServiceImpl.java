package com.jiuyuan.module.sale.service.impl;

import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.mapper.SaleDetailMapper;
import com.jiuyuan.module.sale.mapper.SaleManagementMapper;
import com.jiuyuan.module.sale.service.SaleManagementService;
import com.jiuyuan.utils.NumToCHUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	public List<SaleManagement> findByCondition(Map<String, String> param) {
		return saleManagementService.findByCondition(param);
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

	@Override
	public int deleteByPrimaryKey(String pk) {
		return saleManagementService.deleteByPrimaryKey(pk);
	}

	@Override
	public int deleteByPks(String[] pks) {
        saleDetailMapper.deleteBySalePks(pks);
		return saleManagementService.deleteByPks(pks);
	}

}
