package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Accept;

public interface AcceptDao {
	
	Collection<Accept> acceptList(HashMap <String, Integer> paramMap);
	int size(int memNo);
	
	Collection<Accept> lendDeliveryList(HashMap <String, Integer> paramMap);
	int size1(int memNo);
	int deliveryConfirm(int applyNo);
	
	Collection<Accept> lendList(HashMap <String, Integer> paramMap);
	int size2(int memNo);
	int returnComplConfirm(int applyNo);
	int rentalComplete(int applyNo);
	int rentalStatusY(int applyNo);
	
}
