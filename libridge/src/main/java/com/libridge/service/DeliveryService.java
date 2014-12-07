package com.libridge.service;

import java.util.Collection;

import com.libridge.vo.Delivery;

public interface DeliveryService {

	
/*--Keeping Table------------------------------------------------------------------------------------------------------------*/

	Collection<Delivery> getApplyList(int pageNo, int pageSize, int memNo) throws Exception;
	Collection<Delivery> getDeliveryList(int pageNo, int pageSize, int memNo) throws Exception;
	Collection<Delivery> getBorrowList(int pageNo, int pageSize, int memNo) throws Exception;
	int countApply(int memNo) throws Exception;
	int countDelivery(int memNo) throws Exception;
	int countBorrow(int memNo) throws Exception;
	int receiveDone(int delNo) throws Exception;
	int returnApply(int delNo) throws Exception;
	
	
/*--Donation Table------------------------------------------------------------------------------------------------------------*/
	
	Collection<Delivery> getApplyListD(int pageNo, int pageSize, int memNo) throws Exception;
	Collection<Delivery> getDeliveryListD(int pageNo, int pageSize, int memNo) throws Exception;
	Collection<Delivery> getBorrowListD(int pageNo, int pageSize, int memNo) throws Exception;
	int countApplyD(int memNo) throws Exception;
	int countDeliveryD(int memNo) throws Exception;
	int countBorrowD(int memNo) throws Exception;
	int receiveDoneD(int delNo) throws Exception;
	int readCompleteD(int delNo) throws Exception;
	int rentalStatusY(int persBookNo) throws Exception;

}
