package com.libridge.service;

import com.libridge.vo.ApplicantInfo;

public interface AcceptPageService {
	
	int applicantConfirm(int applyNo) throws Exception;
	int applicantDeny(int applyNo) throws Exception;
	ApplicantInfo getApplicantInfo(int applyNo) throws Exception;
	
	int deliveryReadyStateCode(int applyNo) throws Exception;
	
	int rentalStatusN(int applyNo) throws Exception;
	
}
