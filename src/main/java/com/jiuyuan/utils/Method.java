package com.jiuyuan.utils;

public enum Method {
	ADD("add"), MODIFY("modify"), DELETE("delete");
	
	public final String value;
	
	private Method(String value) {
		this.value = value;
	}
	
}
