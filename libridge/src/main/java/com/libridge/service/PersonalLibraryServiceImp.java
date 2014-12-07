package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.BookDao;
import com.libridge.dao.PersonalLibraryDao;
import com.libridge.vo.MyLibrary;
import com.libridge.vo.PersonalLibrary;

@Service
public class PersonalLibraryServiceImp implements PersonalLibraryService {
	
	@Autowired
	PersonalLibraryDao personalLibraryDao;
	
	@Autowired
	BookDao bookDao;
	
	
//	최초 기부시 DONATION 테이블에 정보를 기입한다.(자신의 PBN을 넣는다)
	@Override
	public int addDonation(int memNo) throws Exception {
		return personalLibraryDao.addDonation(memNo);
	}

//	기부된 책을 신청 후 수취할 때 자신(신청자)의 PL에 정보를 기입한다. DONATION 테이블에 있는 Donation No를 같이 넣는다.
	@Override
	public int donatedBookAdd(int memNo, String isbn, int delNo)
			throws Exception {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memNo", memNo);
		paramMap.put("isbn", isbn);
		paramMap.put("delNo", delNo);
		
		return personalLibraryDao.donatedBook(paramMap);
	}

	@Override
	public int bookCountService(String regCode) {
		return personalLibraryDao.bookCount(regCode);
	}

	@Override
	public Collection<PersonalLibrary> myLibraryBookService(int pageNo, int pageSize, int memNo, String regCode) {
		HashMap<String, Object> paramBook = new HashMap<String, Object>();
		paramBook.put("memNo", memNo);
		paramBook.put("regCode", regCode);
		paramBook.put("pageStartNo", ((pageNo-1) * pageSize));
		paramBook.put("pageSize", pageSize);
		
		return personalLibraryDao.myLibraries(paramBook); 
	}
	
	@Override
	public Collection<PersonalLibrary> myLibraryBookAllService(int pageNo,
			int pageSize, int memNo) throws Exception {
		HashMap<String, Object> paramAllBook = new HashMap<String, Object>();
		paramAllBook.put("pageStartNo", ((pageNo-1) * pageSize));
		paramAllBook.put("pageSize", pageSize);
		paramAllBook.put("memNo", memNo);
		return personalLibraryDao.myLibraryAll(paramAllBook);
	}
	
	@Override
	public int bookAllCountService() throws Exception {
		return personalLibraryDao.bookAllCount();
	}

	public Collection<MyLibrary> donationInfoService (int pageNo, int pageSize, int persBookNo)
			throws Exception {
		HashMap<String, Object> paramDonation = new HashMap<String, Object>();
		paramDonation.put("pageStartNo", ((pageNo-1) * pageSize));
		paramDonation.put("pageSize", pageSize);
		paramDonation.put("persBookNo", persBookNo);
		return personalLibraryDao.donationInfo(paramDonation);
	}

	@Override
	public int addMyBookInfo(int memNo, String regCode, String isbn) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memNo", memNo);
		paramMap.put("regCode", regCode);
		paramMap.put("isbn", isbn);
		
		return personalLibraryDao.addMyBookInfo(paramMap);
	}

}
