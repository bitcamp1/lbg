package com.libridge.vo;

import java.io.Serializable;

public class AjaxResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	String status;
	Object data;
	String error;
	
	
	public String getStatus() {
		return status;
	}
	public AjaxResult setStatus(String status) {
		this.status = status;
		return this;
	}
	public Object getData() {
		return data;
	}
	public AjaxResult setData(Object data) {
		this.data = data;
		return this;
	}
	public String getError() {
		return error;
	}
	public AjaxResult setError(String error) {
		this.error = error;
		return this;
	}

}
