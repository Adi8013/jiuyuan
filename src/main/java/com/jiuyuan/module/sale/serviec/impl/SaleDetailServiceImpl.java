package com.jiuyuan.module.sale.serviec.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuyuan.module.sale.domain.SaleDetail;
import com.jiuyuan.module.sale.mapper.SaleDetailMapper;
import com.jiuyuan.module.sale.serviec.SaleDetailService;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

	@Autowired
	private SaleDetailMapper detailMapper;
	
	@Override
	public List<SaleDetail> queryDetailBySalePk(String salePk) {
		return detailMapper.queryDetailBySalePk(salePk);
	}

	@Override
	public int insertSelective_tran(SaleDetail record) {
		return detailMapper.insertSelective(record);
	}

	@Override
	public int insert_tran(SaleDetail record) {
		return detailMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective_tran(SaleDetail record) {
		return detailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey_tran(SaleDetail record) {
		return detailMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String pk) {
		return detailMapper.deleteByPrimaryKey(pk);
	}

}
