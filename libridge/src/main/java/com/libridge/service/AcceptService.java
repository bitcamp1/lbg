package com.libridge.service;

import java.util.Collection;

import com.libridge.vo.Accept;

public interface AcceptService {
	
/*--Keeping Table------------------------------------------------------------------------------------------------------------*/
	
	Collection<Accept> getAcceptList(int pageNo, int pageSize, int memNo) throws Exception ;
	Collection<Accept> getLendDeliveryList(int pageNo, int pageSize, int memNo) throws Exception;	
	Collection<Accept> getLendList(int pageNo, int pageSize, int memNo) throws Exception;
	int countAccept(int memNo) throws Exception;
	int countLendDelivery(int memNo) throws Exception;
	int countLend(int memNo) throws Exception;
	int deliveryConfirm(int applyNo) throws Exception;
	int returnComplete(int applyNo) throws Exception;
	int rentalComplete(int applyNo) throws Exception;
	int rentalStatusY(int applyNo) throws Exception;
	
/*--Donation Table------------------------------------------------------------------------------------------------------------*/

	Collection<Accept> getAcceptListD(int pageNo, int pageSize, int memNo) throws Exception ;
	Collection<Accept> getLendDeliveryListD(int pageNo, int pageSize, int memNo) throws Exception;	
	int countAcceptD(int memNo) throws Exception;
	int countLendDeliveryD(int memNo) throws Exception;
	int deliveryConfirmD(int applyNo) throws Exception;
	
}

