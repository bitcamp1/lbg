package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class Lend implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer 	applyNo;
	protected Integer 	memNo;
	protected Date 		applyDate;
	protected Date 		assignDate;
	protected Date 		rejectDate;
	protected Integer 	persBookNo;
	protected String 	applyStatusCode;
	
	
	public Integer getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Date getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}
	public Integer getPersBookNo() {
		return persBookNo;
	}
	public void setPersBookNo(Integer persBookNo) {
		this.persBookNo = persBookNo;
	}
	public String getApplyStatusCode() {
		return applyStatusCode;
	}
	public void setApplyStatusCode(String applyStatusCode) {
		this.applyStatusCode = applyStatusCode;
	}
	
	
}
