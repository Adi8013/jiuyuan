package com.jiuyuan.sys.common;

import java.io.Serializable;

public class ResponseMsg implements Serializable{
	String msg = "";
	boolean success = true;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
