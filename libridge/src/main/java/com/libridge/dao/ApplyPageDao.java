package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.ApplyOwners;

public interface ApplyPageDao {
	
	/* 신청시 신청 레코드 insert */
	int applyBook(HashMap<String, Integer> paramMap);
	
	/* 신청 페이지 들어갈 때 나올 책 소유자 정보와 페이징 처리를 하기 위한 DAO */
	Collection<ApplyOwners> applyOwners(HashMap<String,Object> paramMap);
	
	/* 책이 있는 위치 정보와 페이징 처리를 하기 위한 DAO */
	Collection<ApplyOwners> applyBookLoc(HashMap<String,Object> paramMap);
	
	/*페이징 처리를 위한 사이즈 리턴*/
	int size(HashMap<String,Object> paramMap);
	
	/*한달동안 신청 가능한 횟수를 넘었는지 확인하는 로직을 위한 쿼리*/
	int rentalCount(int memNo);
	
	/*책 신청시 차감되는 포인트*/
	int minusPoint(HashMap<String, Integer> paramMap);
	
	/*페이징 처리를 위한 사이즈 리턴(로그인 전 책이 있는 장소에 대한 페이징)*/
	int pbnPagingSize(String isbn);
	
}
