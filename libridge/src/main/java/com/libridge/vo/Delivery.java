package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String 	id;
	protected String 	title;
	protected String 	delCode;
	protected Date 		applyDate;
	protected Date 		sendDate;
	protected Date 		recDate;
	protected String	applyStatusCode;
	protected Integer 	delNo;
	protected String	regCode;
	protected String	isbn;
	protected Integer	persBookNo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDelCode() {
		return delCode;
	}
	public void setDelCode(String delCode) {
		this.delCode = delCode;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
	public String getApplyStatusCode() {
		return applyStatusCode;
	}
	public void setApplyStatusCode(String applyStatusCode) {
		this.applyStatusCode = applyStatusCode;
	}
	public Integer getDelNo() {
		return delNo;
	}
	public void setDelNo(Integer delNo) {
		this.delNo = delNo;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getPersBookNo() {
		return persBookNo;
	}
	public void setPersBookNo(Integer persBookNo) {
		this.persBookNo = persBookNo;
	}

	
}
