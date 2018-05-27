package com.jiuyuan.module.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.serviec.SaleManagementService;

@RestController
@RequestMapping("/sale")
public class SaleController {
	@Autowired
	private SaleManagementService saleService;
	
	@RequestMapping("/salelist")
	public String getUserList() {
		List<SaleManagement> saleList = saleService.findAll();
		String reJSON = JSON.toJSONString(saleList);
		return reJSON;
	}
	
	
	
}
