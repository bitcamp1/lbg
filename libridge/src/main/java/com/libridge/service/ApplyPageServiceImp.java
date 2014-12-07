package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.ApplyPageDao;
import com.libridge.dao.MemberDao;
import com.libridge.vo.ApplyOwners;

@Service
public class ApplyPageServiceImp implements ApplyPageService {

	@Autowired
	ApplyPageDao applyPageDao;
	
	@Autowired
	MemberDao memberDao;
	
	
	@Override
	public int addApplyService(int persBookNo, int memNo) {
		System.out.println("ApplyServiceImp / addApplyService까지 들어옴");
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("persBookNo", persBookNo);
		paramMap.put("memNo", memNo);
		
		return applyPageDao.applyBook(paramMap);
	}
	
	
	@Override
	public Collection<ApplyOwners> getOwnerService(String isbn, int memNo, int pageNo, int pageSize) {
		
		System.out.println("신청 페이지 소유자 정보 ApplyServiceImp : " + isbn);
		
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("isbn", isbn);
		paramMap.put("memNo", memNo);
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		return applyPageDao.applyOwners(paramMap);
	}
	

	@Override
	public Collection<ApplyOwners> getBookLocationService(String isbn, int pageNo, int pageSize) throws Exception {
		
		System.out.println("책이 있는 위치 정보 ApplyServiceImp : " + isbn);
		
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("isbn", isbn);
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		return applyPageDao.applyBookLoc(paramMap);
	}
	
	public int countBookOwner(String isbn, int memNo) throws Exception {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isbn", isbn);
		paramMap.put("memNo", memNo);
		
		return applyPageDao.size(paramMap);
	}
	


	@Override
	public int rentalCount(int memNo) {
		
		return applyPageDao.rentalCount(memNo);
	}


//	로그인을 하지 않을 경우 책 신청 팝업에서 보여줄 책이 있는 장소를 보여줄 때 페이징 처리를 위한 로직
	@Override
	public int pbnPagingSize(String isbn) throws Exception {
		
		return applyPageDao.pbnPagingSize(isbn);
	}

}
