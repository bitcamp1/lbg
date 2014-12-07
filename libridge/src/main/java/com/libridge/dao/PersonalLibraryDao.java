package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.MyLibrary;
import com.libridge.vo.PersonalLibrary;

public interface PersonalLibraryDao {
	
//	최초 기부시 DONATION 테이블에 정보 기입
	int addDonation(int memNo);
	
//	기부된 책을 신청 후 수취할 때 자신의 PL에 정보 기입
	int donatedBook(HashMap<String, Object> paramMap);
	
	int bookCount(String regCode);
	int bookAllCount();
	Collection<PersonalLibrary> myLibraries(HashMap<String, Object> paramBook);
	Collection<PersonalLibrary> myLibraryAll(HashMap<String, Object> paramAllBook);
	Collection<MyLibrary> donationInfo(HashMap<String, Object> paramDonation);
	
	int addMyBookInfo(HashMap<String, Object> paramMap);
	
	
}
