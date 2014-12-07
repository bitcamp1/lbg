package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Accept;

public interface DonationAcceptDao {
	
	Collection<Accept> acceptList(HashMap <String, Integer> paramMap);
	int size(int memNo);
	
	Collection<Accept> lendDeliveryList(HashMap <String, Integer> paramMap);
	int size1(int memNo);
	
	int deliveryConfirm(int applyNo);
	
}
