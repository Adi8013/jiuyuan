package com.jiuyuan.module.sale.controller;

import com.alibaba.fastjson.JSON;
import com.jiuyuan.module.common.service.CommonService;
import com.jiuyuan.module.sale.domain.ExcelSaleDetail;
import com.jiuyuan.module.sale.domain.SaleDetail;
import com.jiuyuan.module.sale.domain.SaleManagement;
import com.jiuyuan.module.sale.domain.SearchCondition;
import com.jiuyuan.module.sale.service.SaleDetailService;
import com.jiuyuan.module.sale.service.SaleManagementService;
import com.jiuyuan.sys.common.ResponseMsg;
import com.jiuyuan.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleManagementService saleService;
    @Autowired
    private SaleDetailService saleDetailService;
    @Autowired
    private CommonService commonService;


    @GetMapping(value = "/exportExcel/{pk}/{saleNo}")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @PathVariable("pk") String salePk, @PathVariable("saleNo") String saleNo) {
        // 创建文件输出流，准备输出电子表格
        String excel_name =saleNo + ".xls";
        OutputStream out = null;
//        System.out.println(request.getRequestURI());
        response.setContentType("application/x-download");
        response.setHeader("content-disposition", "attachment; filename=" + excel_name);
//        response.setHeader("Content-type", "charset=UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        try {
            out = response.getOutputStream();
            // 单元格中文标题
            String[] headers = {"产品名称", "型号规格", "单位", "数量", "单价", "金额", "备注", "专营"};
            List<SaleDetail> details = saleDetailService.queryDetailBySalePk(salePk);
            SaleManagement sale = saleService.selectByPrimaryKey(salePk);
            List<ExcelSaleDetail> excelModels = new ArrayList<ExcelSaleDetail>();
            for (SaleDetail detail : details) {
                excelModels.add(ExcelSaleDetail.changeToExcelModle(detail));
            }
            // 第一个Excel实体类加上专营
            excelModels.get(0).setMonopoly("九元牌高端高温琉璃瓦、仿古瓦、青灰瓦、各式花窗、多种规格大青砖、收工青砖片、等园林建筑产品，欢迎来电咨询");
            int flag_size = excelModels.size();
            // 不足9行, 补充空行直到有9行
            if (flag_size < 9) {
                for (int i = 0; i < 9 - flag_size; i++) {
                    excelModels.add(new ExcelSaleDetail());
                }
            }
            // 倒数第二个Excel实体类加上专营
            excelModels.get(excelModels.size() - 2).setMonopoly("商品请当面验收非质量问题恕不退换");
            ExportExcel<ExcelSaleDetail> excel = new ExportExcel<ExcelSaleDetail>();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("receiver", "收货单位：  " + sale.getReceiver());
            paramMap.put("saleNo", "No. " + sale.getSaleNo());
            paramMap.put("address", "地  址：  " + sale.getAddress());
            paramMap.put("date", sale.getCreateDate());
            Double sum = saleDetailService.querySumPriceBySaleNo(saleNo);
            paramMap.put("sum", sum.toString());
            paramMap.put("heji", "合      计");
            paramMap.put("operator", "制单员： " + sale.getOperator());
            paramMap.put("CN", "大写人民币：" + NumToCHUtil.numToCN(new BigDecimal(sum)));
            paramMap.put("customer", "客户签收： ");
            paramMap.put("factory", "佛山地址： 佛山市禅城区南庄村建材物流仓A区1座");
            paramMap.put("phone1", "服务热线： 0757-82564939");
            paramMap.put("shenzhen", "");
            paramMap.put("phone2", "移动电话： 13302801169");

            excel.exportExcel(3, saleNo, headers, excelModels, out, paramMap);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
    @PostMapping(value = "deleteSale")
    @ResponseBody
    public String deleteSale(@RequestParam(value = "pks[]") String[] pks) {
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
    @PostMapping(value = "search")
    @ResponseBody
    public String search(@RequestBody SearchCondition searchCondition) {
        String json = JSON.toJSONString(searchCondition);
        Map params = JSON.parseObject(json, Map.class);
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
        if (flagSalePk == null || flagSalePk.equals("")) {
            // 创建销售单
            SaleManagement sale = new SaleManagement();
            // 创建日期
            sale.setCreateDate(DateUtil.getDateInChinese(DateUtil.getSpecifyDate(new Date())));
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
