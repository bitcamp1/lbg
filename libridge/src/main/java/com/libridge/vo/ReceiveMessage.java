package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class ReceiveMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer 	recMsgNo;
	protected String 	contents;
	protected String 	title;
	protected String 	recMemId;
	protected Date 		recDate;
	protected Integer 	memNo;
	
	
	public Integer getRecMsgNo() {
		return recMsgNo;
	}
	public void setRecMsgNo(Integer recMsgNo) {
		this.recMsgNo = recMsgNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecMemId() {
		return recMemId;
	}
	public void setRecMemId(String recMemId) {
		this.recMemId = recMemId;
	}
	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	
	
	
}


