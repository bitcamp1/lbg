package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Delivery;

public interface DonationDeliveryDao {

	Collection<Delivery> applyList(HashMap<String, Integer> paramMap);
	Collection<Delivery> borrowDeliveryList(HashMap<String, Integer> paramMap);
	Collection<Delivery> borrowList(HashMap<String, Integer> paramMap);
	
	int receiveConfirm(int delNo);
	int readComplete(int delNo);
	int rentalStatusY(int persBookNo);
	
	int size(int memNo);
	int size1(int memNo);
	int size2(int memNo);
}
