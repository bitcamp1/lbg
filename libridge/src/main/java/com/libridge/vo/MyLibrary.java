package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class MyLibrary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Integer 	persBookNo;
	protected Integer 	memNo;
	protected String 	regCode;
	protected String 	isbn;
	protected String 	rentalStatus;
	protected String 	title;
	protected String 	author;            
	protected Integer 	price;               
	protected String 	pubDate;            
	protected String 	pub;            
	protected String 	trans;           
	protected String 	typeCode;          
	protected Date 		regDate;  
	protected String 	bookImgUrl;
	protected Integer 	lendMemNo;
	protected Integer 	applyNo;
	protected Date 		applyDate;
	protected Date 		assignDate;
	protected Date 		rejectDate;
	protected String 	applyStatusCode;
	protected String 	addr1;
	protected String 	name;
	
	public Integer getPersBookNo() {
		return persBookNo;
	}
	public void setPersBookNo(Integer persBookNo) {
		this.persBookNo = persBookNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
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
	public String getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getBookImgUrl() {
		return bookImgUrl;
	}
	public void setBookImgUrl(String bookImgUrl) {
		this.bookImgUrl = bookImgUrl;
	}
	public Integer getLendMemNo() {
		return lendMemNo;
	}
	public void setLendMemNo(Integer lendMemNo) {
		this.lendMemNo = lendMemNo;
	}
	public Integer getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
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
	public String getApplyStatusCode() {
		return applyStatusCode;
	}
	public void setApplyStatusCode(String applyStatusCode) {
		this.applyStatusCode = applyStatusCode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
