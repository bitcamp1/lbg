package com.libridge.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.AcceptPageDao;
import com.libridge.vo.ApplicantInfo;

@Service
public class AcceptPageServiceImp implements AcceptPageService {

	@Autowired
	AcceptPageDao accpetPageDao;
	
	
	@Override
	public int applicantConfirm(int applyNo) {
		System.out.println("AcceptPageServiceImp / applicantConfirm까지 들어옴");
		
		return accpetPageDao.applicantConfirm(applyNo);
	}
	
	
	@Override
	public ApplicantInfo getApplicantInfo(int applyNo) {
		
		System.out.println("수락 페이지 소유자 정보 AcceptPageServiceImp : " + applyNo);
		
		return accpetPageDao.applicantInfo(applyNo);
	}


	@Override
	public int deliveryReadyStateCode(int applyNo) throws Exception {
		
		System.out.println("신청 페이지 deliveryReadyState : " + applyNo);
		
		return accpetPageDao.deliveryReadyState(applyNo);
		
	}


	@Override
	public int applicantDeny(int applyNo) throws Exception {
		System.out.println("AcceptPageServiceImp / applicantDeny까지 들어옴");
		
		return accpetPageDao.applicantDeny(applyNo);
	}
	

	@Override
	public int rentalStatusN(int applyNo) throws Exception {
		return accpetPageDao.rentalStatusN(applyNo);
	}
	
}
