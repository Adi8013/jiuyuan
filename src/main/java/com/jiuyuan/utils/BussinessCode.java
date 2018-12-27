package com.jiuyuan.utils;

import com.jiuyuan.module.common.service.CommonService;
import com.jiuyuan.module.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public enum BussinessCode {

    /**
     * 销售单编号 JYYYYY-xxx
     */
    JYXSD("JYXSD", "tSaleManagement", "SaleNo") {
        @Override
        public String getNextCode(CommonService commonService) {
            String maxCode = "";
            String date = getDate(maxCode, 0, 4, null);
            String selectCode = commonService.seleceMaxBussCode(this.tableName, this.columnNmae, "JY" + date + "-%");
            if (selectCode != null && !selectCode.equals("")) {
                maxCode = selectCode;
            } else {
                return "JY" + date + "-001";
            }
            return getMaxNo(maxCode);
        }
    };

    /**
     * 获取最大业务编码
     *
     * @param maxCode 从数据查询到的最大编号
     * @return
     */
    public String getMaxNo(String maxCode) {
        String[] temp = maxCode.split("-");
        Integer num = Integer.valueOf(temp[1]);
        if (num < 9) {
            return temp[0] + "-" + "00" + (num + 1);
        } else if (num == 9) {
            return temp[0] + "-" + "010";
        } else if (num < 99) {
            return temp[0] + "-" + "0" + (num + 1);
        } else if (num == 99) {
            return temp[0] + "-" + "100";
        } else {
            return temp[0] + "-" + (num + 1);
        }
    }

    //    public abstract String getNextCode(String... params);
    public abstract String getNextCode(CommonService commonService);

    /**
     * 枚举构造器
     */
    private BussinessCode(String businType, String tableName, String columnNmae) {
        this.businType = businType;
        this.columnNmae = columnNmae;
        this.tableName = tableName;
    }

    /**
     * 业务类型
     */
    protected String businType = "";
    /**
     * 编号字段名
     */
    protected String columnNmae = "";
    /**
     * 表名
     */
    protected String tableName = "";

    // 通用服务接口
    @Resource(name = "commonService")
    public CommonService commonService;

    /**
     * 获取时间，默认时间格式为yyyyMMddHHmmssSSS
     *
     * @param date    指定时间,格式为yyyy-MM-dd
     * @param dateLen 日期的长度，若大于等于零，则截取前段；否则截取后段。
     * @return 截取后的日期，若date为空，则截取当前日期。
     */
    public String getNowDate(String date, int dateLen) {
        String nowDate = "";
        try {
            if (date == null || date.length() <= 0) {
                //String，格式为'yyyyMMddHHmmssSSS'的系统当前日期。
                nowDate = DateUtil.getSystemDateTimeSerialNo();
            } else {
                nowDate = date.replaceAll("-", "") + "000000000";
            }
            if (dateLen >= 0) {//截取前半段
                nowDate = nowDate.substring(0, dateLen);
            } else {//截取后半段
                nowDate = nowDate.substring(nowDate.length() + dateLen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowDate;
    }

    /**
     * 获取编号日期YYYY
     *
     * @param queryMaxNO     最大编号
     * @param dateStartIndex 日期起始下标
     * @param dateLen        日期长度
     * @param date           指定日期，若为null则取当前日期
     * @return 编号中的日期
     */
    public String getDate(String queryMaxNO, int dateStartIndex, int dateLen, String date) {
        String nowDate = getNowDate(date, dateLen);
        if (queryMaxNO == null || queryMaxNO.length() <= 0 || queryMaxNO.indexOf(nowDate) == -1) {
            return nowDate;
        }
        return queryMaxNO.substring(dateStartIndex, dateStartIndex + dateLen);
    }
}
