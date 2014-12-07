package com.libridge.dao;

import java.util.HashMap;

import com.libridge.vo.ApplicantInfo;

public interface AcceptPageDao {
	
	ApplicantInfo applicantInfo(int applyNo);
	
	int deliveryReadyState(int applyNo);
	
	int applicantConfirm(int applyNo);
	
	int applicantDeny(int applyNo);
	
	int rentalStatusN(int applyNo);
	
}
