package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class DeliveryVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer 	delNo;
	protected String 	delCode;
	protected Date 		sendDate;
	protected Date 		recDate;
	protected Date 		retDate;
	protected Date 		retComplDate;
	protected Integer 	applyNo;
	
	
	public Integer getDelNo() {
		return delNo;
	}
	public void setDelNo(Integer delNo) {
		this.delNo = delNo;
	}
	public String getDelCode() {
		return delCode;
	}
	public void setDelCode(String delCode) {
		this.delCode = delCode;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	public Date getRetDate() {
		return retDate;
	}
	public void setRetDate(Date retDate) {
		this.retDate = retDate;
	}
	public Date getRetComplDate() {
		return retComplDate;
	}
	public void setRetComplDate(Date retComplDate) {
		this.retComplDate = retComplDate;
	}
	public Integer getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}

}
