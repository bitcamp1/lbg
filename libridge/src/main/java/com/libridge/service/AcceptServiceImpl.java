package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.AcceptDao;
import com.libridge.dao.DonationAcceptDao;
import com.libridge.vo.Accept;

@Service
public class AcceptServiceImpl implements AcceptService {
	
	@Autowired
	AcceptDao acceptDao; 
	
	@Autowired
	DonationAcceptDao donationAcceptDao;

	
/*--Keeping Table------------------------------------------------------------------------------------------------------------*/

	@Override
	public Collection<Accept> getAcceptList(int pageNo, int pageSize, int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		
		return acceptDao.acceptList(paramMap);
	}

	@Override
	public int countAccept(int memNo) throws Exception {
		
		return acceptDao.size(memNo);
	}

	@Override
	public Collection<Accept> getLendDeliveryList(int pageNo, int pageSize,
			int memNo) throws Exception {
		HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		
		return acceptDao.lendDeliveryList(paramMap);
	}

	@Override
	public int countLendDelivery(int memNo) throws Exception {
		
		return acceptDao.size1(memNo);
	}

	@Override
	public Collection<Accept> getLendList(int pageNo, int pageSize, int memNo)
			throws Exception {
			HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
			paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
			paramMap.put("pageSize", pageSize);
			paramMap.put("memNo", memNo);
			
			return acceptDao.lendList(paramMap);
	}

	@Override
	public int countLend(int memNo) throws Exception {
		
		return acceptDao.size2(memNo);
	}
	
	@Override
	public int deliveryConfirm(int applyNo) throws Exception {
		
		return acceptDao.deliveryConfirm(applyNo);
	}

	@Override
	public int returnComplete(int applyNo) throws Exception {
		
		return acceptDao.returnComplConfirm(applyNo);
	}

	@Override
	public int rentalComplete(int applyNo) throws Exception {
		
		return acceptDao.rentalComplete(applyNo);
	}

	@Override
	public int rentalStatusY(int applyNo) throws Exception {
		
		return acceptDao.rentalStatusY(applyNo);
	}

	
/*--Donation Table------------------------------------------------------------------------------------------------------------*/
	

	@Override
	public Collection<Accept> getAcceptListD(int pageNo, int pageSize, int memNo)
			throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		
		return donationAcceptDao.acceptList(paramMap);
	}
	
	@Override
	public int countAcceptD(int memNo) throws Exception {
		
		return donationAcceptDao.size(memNo);
	}

	@Override
	public Collection<Accept> getLendDeliveryListD(int pageNo, int pageSize,
			int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap <String, Integer> ();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("memNo", memNo);
		
		return donationAcceptDao.lendDeliveryList(paramMap);
	}

	@Override
	public int countLendDeliveryD(int memNo) throws Exception {
		
		return donationAcceptDao.size1(memNo);
	}

	@Override
	public int deliveryConfirmD(int applyNo) throws Exception {
		
		return donationAcceptDao.deliveryConfirm(applyNo);
	}

}
