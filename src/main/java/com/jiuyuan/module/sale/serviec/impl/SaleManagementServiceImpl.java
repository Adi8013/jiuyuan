package com.jiuyuan.module.sale.serviec.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.mapper.SaleManagementMapper;
import com.jiuyuan.module.sale.serviec.SaleManagementService;

@Service
public class SaleManagementServiceImpl implements SaleManagementService {

	@Autowired
	private SaleManagementMapper saleManagementService;
	
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
		return saleManagementService.selectByPrimaryKey(pk);
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
