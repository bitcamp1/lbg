package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Delivery;

public interface DeliveryDao {

	Collection<Delivery> applyList(HashMap<String, Integer> paramMap);
	Collection<Delivery> borrowDeliveryList(HashMap<String, Integer> paramMap);
	Collection<Delivery> borrowList(HashMap<String, Integer> paramMap);
	int receiveConfirm(int delNo);
	int returnApply(int delNo);
	int size(int memNo);
	int size1(int memNo);
	int size2(int memNo);
}
