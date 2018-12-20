package com.jiuyuan.module.sale.serviec;

import com.jiuyuan.module.sale.domain.SaleManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional()
public interface SaleManagementService {

	@Transactional(readOnly = true)
	List<SaleManagement> findAll();

	int insertSelective_tran(SaleManagement record);

	int insert_tran(SaleManagement record);

	@Transactional(readOnly = true)
	SaleManagement selectByPrimaryKey(String pk);

	int updateByPrimaryKeySelective_tran(SaleManagement record);

	int updateByPrimaryKey_tran(SaleManagement record);
}
