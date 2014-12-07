package com.libridge.vo;

import java.io.Serializable;

public class ApplyOwners implements Serializable {
	private static final long serialVersionUID = 1L;
  
	protected Integer	memNo;
	protected String	id;
	protected String 	name;
	protected String	addr1;
	protected String 	phoNo;
	protected String 	email;
	protected String	photoUrl;
	protected String 	regCode;
	protected Integer	persBookNo;
	
	
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getPhoNo() {
		return phoNo;
	}
	public void setPhoNo(String phoNo) {
		this.phoNo = phoNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public Integer getPersBookNo() {
		return persBookNo;
	}
	public void setPersBookNo(Integer persBookNo) {
		this.persBookNo = persBookNo;
	}

}
