package com.jiuyuan.module.sale.domain;

public class SaleManagement {
    private String pk;

    private String saleNo;

    private String operator;

    private String receiver;

    private String address;
    
    private String createDate;

    private String remark;

    private String insertTime;

    private String lastestUpdate;

    private String updatePerson;
    
    /** 总金额*/
    private Double totalPrice;
    /** 人民币大写*/
    private String rmbCH;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getLastestUpdate() {
        return lastestUpdate;
    }

    public void setLastestUpdate(String lastestUpdate) {
        this.lastestUpdate = lastestUpdate;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRmbCH() {
		return rmbCH;
	}

	public void setRmbCH(String rmbCH) {
		this.rmbCH = rmbCH;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}