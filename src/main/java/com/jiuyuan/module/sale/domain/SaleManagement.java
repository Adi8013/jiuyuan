package com.jiuyuan.module.sale.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

public class SaleManagement implements Serializable{
	
	public interface SaleSimpleView {};
    public interface SaleDetailView extends SaleSimpleView {};
	
    private String pk;
    
    private String saleNo;
    
    private String operator;

    private String productName;

    private String size;

    private String unit;

    private Integer quantity;

    private BigDecimal perPrice;

    private String remark;

    private String insertTime;

    private String lastestUpdate;

    private String updatePerson;
    
    @JsonView(SaleSimpleView.class)
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
    @JsonView(SaleSimpleView.class)
    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }
    @JsonView(SaleSimpleView.class)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    @JsonView(SaleDetailView.class)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @JsonView(SaleDetailView.class)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    @JsonView(SaleDetailView.class)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    @JsonView(SaleDetailView.class)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @JsonView(SaleDetailView.class)
    public BigDecimal getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(BigDecimal perPrice) {
        this.perPrice = perPrice;
    }
    @JsonView(SaleSimpleView.class)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @JsonView(SaleSimpleView.class)
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }
    @JsonView(SaleSimpleView.class)
    public String getLastestUpdate() {
        return lastestUpdate;
    }

    public void setLastestUpdate(String lastestUpdate) {
        this.lastestUpdate = lastestUpdate;
    }
    @JsonView(SaleSimpleView.class)
    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
}