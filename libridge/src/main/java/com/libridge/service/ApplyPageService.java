package com.libridge.service;

import java.util.Collection;

import com.libridge.vo.ApplyOwners;

public interface ApplyPageService {
	
	int addApplyService(int persBookNo, int memNo) throws Exception;
	
//	로그인 한 경우 소유자 정보들
	Collection<ApplyOwners> getOwnerService(String isbn, int memNo, int pageNo, int pageSize) throws Exception;
	
//	로그인 이전 어느 지역에 책이 있는지에 대한 정보
	Collection<ApplyOwners> getBookLocationService(String isbn, int pageNo, int pageSize) throws Exception;
	
	int countBookOwner(String isbn, int memNo) throws Exception;
	int rentalCount(int memNo) throws Exception;

	int pbnPagingSize(String isbn) throws Exception;
	
}
