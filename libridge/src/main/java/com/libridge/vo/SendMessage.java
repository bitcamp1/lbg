package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class SendMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer 	sendMsgNo;
	protected String 	sendMemId;
	protected Date 		sendDate;
	protected String 	contents;
	protected String 	title;
	protected Integer 	memNo;
	
	
	public Integer getSendMsgNo() {
		return sendMsgNo;
	}
	public void setSendMsgNo(Integer sendMsgNo) {
		this.sendMsgNo = sendMsgNo;
	}
	public String getSendMemId() {
		return sendMemId;
	}
	public void setSendMemId(String sendMemId) {
		this.sendMemId = sendMemId;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	
	
}


