package com.jiuyuan.module.sale.serviec;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jiuyuan.module.sale.domain.SaleManagement;

@Transactional()
public interface SaleManagementService {

	@Transactional(readOnly = true)
	List<SaleManagement> findAll();

	int insertSelective_tran(SaleManagement record);

	int insert_tran(SaleManagement record);

	// 根据主键批量删除用户
	int deleteByPks(String[] pks);
	
	@Transactional(readOnly = true)
	SaleManagement selectByPrimaryKey(String pk);

	int updateByPrimaryKeySelective_tran(SaleManagement record);

	int updateByPrimaryKey_tran(SaleManagement record);
}
