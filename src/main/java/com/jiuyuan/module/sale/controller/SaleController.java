package com.jiuyuan.module.sale.controller;

import com.alibaba.fastjson.JSON;
import com.jiuyuan.module.common.service.CommonService;
import com.jiuyuan.module.sale.domain.SaleDetail;
import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.domain.SearchCondition;
import com.jiuyuan.module.sale.service.SaleDetailService;
import com.jiuyuan.module.sale.service.SaleManagementService;
import com.jiuyuan.sys.common.ResponseMsg;
import com.jiuyuan.utils.BussinessCode;
import com.jiuyuan.utils.Method;
import com.jiuyuan.utils.OperatorUtil;
import com.jiuyuan.utils.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class SaleController {
	@Autowired
	private SaleManagementService saleService;
	@Autowired
	private SaleDetailService saleDetailService;
	@Autowired
	private CommonService commonService;

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
	public String getSaleList() {
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
	 * 销售单删除
	 *
	 * @return
	 */
	@PostMapping(value="deleteSale")
	@ResponseBody
	public String deleteSale(@RequestParam(value="pks[]")String[] pks) {
		int result = saleService.deleteByPks(pks);
		if (result > 0) {
			return SystemConstant.SUCCESS;
		} else {
			return SystemConstant.ERROR;
		}
	}

	/**
	 * 销售单查询
	 *
	 * @return
	 */
	@PostMapping(value="search")
	@ResponseBody
	public String search(@RequestBody SearchCondition searchCondition) {
		String json = JSON.toJSONString(searchCondition);
		Map params = JSON.parseObject(json,Map.class);
		List<SaleManagement> saleList = saleService.findByCondition(params);
		String reJSON = JSON.toJSONString(saleList);
		return reJSON;
	}

	/**
	 * 销售单修改
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

	@PostMapping("addSale")
	@ResponseBody
	public ResponseMsg addSale(@RequestBody List<SaleDetail> saleDetails) {
		if (saleDetails == null || saleDetails.isEmpty()) {
			return ResponseMsg.failed("销售单明细为空！");
		}
		// 无salePk和saleNo则为新增
        String flagSalePk = saleDetails.get(0).getSalePk();
        if ( flagSalePk == null || flagSalePk.equals("")) {
			// 创建销售单
			SaleManagement sale = new SaleManagement();
			// 销售单号
			String saleNo = BussinessCode.JYXSD.getNextCode(commonService);
//		System.out.println("销售单编号" + saleNo);
			sale.setSaleNo(saleNo);
			// 收货单位
			sale.setReceiver(saleDetails.get(0).getReceiver());
			// 地址
			sale.setAddress(saleDetails.get(0).getAddress());
			// 制单员(当前系统账户)
			sale.setOperator(OperatorUtil.getOperatorInfo()[1]);
			// 保存销售单
			int result = saleService.insert_tran(sale);
			if (result <= 0) return ResponseMsg.failed("销售单创建失败");

			// 为所有销售明细添加销售单pk和销售单编号
			String salePK = sale.getPk();
			for (SaleDetail sales : saleDetails) {
				sales.setSalePk(salePK);
				sales.setSaleNo(saleNo);
			}
//		boolean re = saleDetailService.insertListDetail_tran(saleDetails);
			for (SaleDetail saleDetail : saleDetails) {
				int re = saleDetailService.insert_tran(saleDetail);
				if (re <= 0) {
					// 结果有误， 删除对应销售单
					saleService.deleteByPrimaryKey(salePK);
					return ResponseMsg.failed("销售单添加失败！");
				}
			}
			return ResponseMsg.success("保存成功");
        } else { // 修改销售单
        	// 找到该销售单
			SaleManagement sale = saleService.selectByPrimaryKey(flagSalePk);
			// 两个由页面修改的值
			// 收货单位
			sale.setReceiver(saleDetails.get(0).getReceiver());
			// 地址
			sale.setAddress(saleDetails.get(0).getAddress());
			// 修改销售单
			saleService.updateByPrimaryKey_tran(sale);
			/**销售单明细处理*/
			saleDetailService.deleteBySalePks(new String[]{flagSalePk});
			for (SaleDetail saleDetail : saleDetails) {
				int re = saleDetailService.insert_tran(saleDetail);
				if (re <= 0) {
					return ResponseMsg.failed("销售单修改失败！");
				}
			}
			return ResponseMsg.success("修改成功");
		}
	}

}
