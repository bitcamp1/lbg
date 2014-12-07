package com.libridge.service;

import java.util.Collection;

import com.libridge.vo.MyLibrary;
import com.libridge.vo.PersonalLibrary;

public interface PersonalLibraryService {
	
	int donatedBookAdd(int memNo, String isbn, int delNo) throws Exception;
	
	int addDonation(int memNo) throws Exception;
	
	int bookCountService(String regCode) throws Exception;
	int bookAllCountService() throws Exception;
	Collection<PersonalLibrary> myLibraryBookService(int pageNo, int pageSize, int memNo, String regCode) throws Exception;
	Collection<PersonalLibrary> myLibraryBookAllService(int pageNo, int pageSize, int memNo) throws Exception ;
	Collection<MyLibrary> donationInfoService(int pageNo, int pageSize, int persBookNo) throws Exception;
	
	int addMyBookInfo(int memNo, String regCode, String isbn) throws Exception;
}
