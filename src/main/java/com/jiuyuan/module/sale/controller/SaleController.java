package com.jiuyuan.module.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.serviec.SaleDetailService;
import com.jiuyuan.module.sale.serviec.SaleManagementService;
import com.jiuyuan.utils.Method;

@Controller
@RequestMapping("/sale")
public class SaleController {
	@Autowired
	private SaleManagementService saleService;
	@Autowired
	private SaleDetailService saleDetailService;

	/**
	 * 销售单管理列表
	 * 
	 * @return
	 */
	@RequestMapping("/salejsp")
	public String sale() {
		return "module/salemanager/salemanager";
	}

	@RequestMapping("/salelist")
	@ResponseBody
	public String getUserList() {
		List<SaleManagement> saleList = saleService.findAll();
		String reJSON = JSON.toJSONString(saleList);
		return reJSON;
	}

	/**
	 * 销售单管理查看
	 * 
	 * @return
	 */
	@RequestMapping("saleview")
	public String saleView(String salePk, Model model) {
		model.addAttribute("saleDetails", saleDetailService.queryDetailBySalePk(salePk));
		model.addAttribute("sale", saleService.selectByPrimaryKey(salePk));
		return "module/salemanager/veiwSale";
	}

	/**
	 * 销售单添加
	 * 
	 * @return
	 */
	@RequestMapping("editSale")
	public String editSale(String method, String salePk, Model model) {
		if (method.equals(Method.MODIFY.value)) {
			model.addAttribute("saleDetails", saleDetailService.queryDetailBySalePk(salePk));
			model.addAttribute("sale", saleService.selectByPrimaryKey(salePk));
		}
		return "module/salemanager/editSale";
	}

}
