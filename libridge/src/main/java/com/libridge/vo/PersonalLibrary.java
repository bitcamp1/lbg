package com.libridge.vo;

import java.io.Serializable;
import java.util.Date;

public class PersonalLibrary implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected Integer 			persBookNo;
	protected Integer 			memNo;
	protected String 			regCode;
	protected String 			isbn;
	protected String 			title;                
	protected String 			author;            
	protected Integer 			price;               
	protected String 			pubDate;            
	protected String 			pub;            
	protected String 			trans;           
	protected String 			typeCode;          
	protected Date 				regDate;  
	protected String 			bookImgUrl;
	protected String			rentalStatus;
	protected Integer			doNo;
	
	
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
	public String getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	public Integer getDoNo() {
		return doNo;
	}
	public void setDoNo(Integer doNo) {
		this.doNo = doNo;
	}
	
	
	
}