package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.DeliveryDao;
import com.libridge.dao.DonationDeliveryDao;
import com.libridge.vo.Delivery;


@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	DeliveryDao deliveryDao;
	
	@Autowired
	DonationDeliveryDao donationDeliveryDao;

	
/*--Keeping Table------------------------------------------------------------------------------------------------------------*/
	
	@Override
	public Collection<Delivery> getApplyList(int pageNo, int pageSize, int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		return deliveryDao.applyList(paramMap);
	}

	
	@Override
	public int countApply(int memNo) throws Exception {
		return deliveryDao.size(memNo);
	}
	
	
	@Override
	public Collection<Delivery> getDeliveryList(int pageNo, int pageSize, int memNo)
			throws Exception {
		
		System.out.println("DeliverySeriveImpl -> getDeliveryList");
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		return deliveryDao.borrowDeliveryList(paramMap);
	}

	@Override
	public int countDelivery(int memNo) throws Exception {
		return deliveryDao.size1(memNo);
	}
	
	
	@Override
	public Collection<Delivery> getBorrowList(int pageNo, int pageSize, int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize",pageSize);
		paramMap.put("memNo", memNo);

		return deliveryDao.borrowList(paramMap);
	}

	@Override
	public int countBorrow(int memNo) throws Exception {
		
		return deliveryDao.size2(memNo);
	}
	
	@Override
	public int receiveDone(int delNo) throws Exception {
		
		return deliveryDao.receiveConfirm(delNo);
	}
	

	@Override
	public int returnApply(int delNo) throws Exception {
		
		return deliveryDao.returnApply(delNo);
	}

/*--Donation Table------------------------------------------------------------------------------------------------------------*/
	
	@Override
	public Collection<Delivery> getApplyListD(int pageNo, int pageSize, int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		return donationDeliveryDao.applyList(paramMap);
	}

	
	@Override
	public int countApplyD(int memNo) throws Exception {
		return donationDeliveryDao.size(memNo);
	}
	
	
	@Override
	public Collection<Delivery> getDeliveryListD(int pageNo, int pageSize, int memNo)
			throws Exception {
		
		System.out.println("DeliverySeriveImpl -> getDeliveryListD");
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		return donationDeliveryDao.borrowDeliveryList(paramMap);
	}

	@Override
	public int countDeliveryD(int memNo) throws Exception {
		return donationDeliveryDao.size1(memNo);
	}
	

	@Override
	public Collection<Delivery> getBorrowListD(int pageNo, int pageSize, int memNo) 
			throws Exception {
		
		System.out.println("DeliverySeriveImpl -> getBorrowListD");
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		return donationDeliveryDao.borrowList(paramMap);
	}
	
	
	@Override
	public int countBorrowD(int memNo) throws Exception {
		return donationDeliveryDao.size2(memNo);
	}
	
	
	@Override
	public int receiveDoneD(int delNo) throws Exception {
		
		return donationDeliveryDao.receiveConfirm(delNo);
	}


	@Override
	public int readCompleteD(int delNo) throws Exception {
		
		return donationDeliveryDao.readComplete(delNo);
	}


	@Override
	public int rentalStatusY(int persBookNo) throws Exception {
		
		return donationDeliveryDao.rentalStatusY(persBookNo);
	}
	
}