package com.jiuyuan.module.sale.domain;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ExcelSaleDetail {
    private String productName;

    private String size;

    private String unit;

    private Integer quantity;

    private BigDecimal perPrice;

    private BigDecimal price = BigDecimal.valueOf(0.00).setScale(2);

    private String remark;

    private String monopoly;

    public static ExcelSaleDetail changeToExcelModle(SaleDetail saleDetail) {
        // 利用反射， 根据JavaBean属性的先后顺序，动态调用getXXX（）方法得到属性值
        ExcelSaleDetail excelModel = new ExcelSaleDetail();
        Field[] fields = saleDetail.getClass().getDeclaredFields();
        Field[] fieldsExcel = excelModel.getClass().getDeclaredFields();
        for (int i = 0; i < fieldsExcel.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fieldsExcel[i].getName().equals(fields[j].getName())) {
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class<? extends SaleDetail> tCls = saleDetail.getClass();
                    Class<? extends ExcelSaleDetail> tExcel = excelModel.getClass();
                    try {
                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        Class[] parameterTypes = new Class[1];
                        parameterTypes[0] = field.getType();
                        Method setMethod = tExcel.getMethod(setMethodName, parameterTypes);
                        Object value = getMethod.invoke(saleDetail, new Object[]{});
                        setMethod.invoke(excelModel, new Object[]{value});
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        BigDecimal price = excelModel.getPerPrice().multiply(BigDecimal.valueOf(excelModel.getQuantity()));
        price.setScale(2);
        excelModel.setPrice(price);
        return excelModel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(BigDecimal perPrice) {
        this.perPrice = perPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(String monopoly) {
        this.monopoly = monopoly;
    }
}
