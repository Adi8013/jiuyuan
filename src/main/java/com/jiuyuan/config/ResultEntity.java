package com.jiuyuan.config;

public class ResultEntity<T> {
	private boolean success = true;
	private String message;
	private T entity;

	public ResultEntity() {
	}

	public ResultEntity(boolean success, String message, T entity) {
		this.success = success;
		this.message = message;
		this.entity = entity;
	}

	public static <T> ResultEntity success(T entity) {
		return new ResultEntity(true, null, entity);
	}

	public static <T> ResultEntity fail(String message, T entity) {
		return new ResultEntity(false, message, entity);
	}

	public static <T> ResultEntity fail(String message) {
		return fail(message, null);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
