package com.jiuyuan.sys.common;

import java.io.Serializable;

public class ResponseMsg implements Serializable{
	String msg = "";
	String status = "";
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
