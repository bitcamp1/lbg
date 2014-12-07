package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.BookDao;
import com.libridge.dao.MemberDao;
import com.libridge.vo.Book;
import com.libridge.vo.Member;

@Service
public class BookServiceImp implements BookService {

	@Autowired
	BookDao bookDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public int addBookService(Book book) {
		return bookDao.addBook(book);
	}
	
	@Override
	public Book getBookService(String isbn) {
		System.out.println("여기는 bookServiceImp에 들어온 isbn 값 : "+isbn);
		return bookDao.getBook(isbn);
	}
	
	@Override
	public Collection<Member> getMembers(int memberNumber) {
		HashMap<String, String> memberHashMap= new HashMap<String, String>();
		
		return null;
	}

	@Override
	public Collection<Book> getMainBook(Member member) {
		System.out.println("메인 화면 책 목록 정보");
		return bookDao.getMainBook(member);
	}
	
	@Override
	public Collection<Book> getDbSearch(String keyword) {
		return bookDao.getDbSearch(keyword);
	}

	@Override
	public int numOfOneBookK(String isbn) {
		return bookDao.numOfOneBookKeeping(isbn);
	}

	@Override
	public int numOfOneBookD(String isbn) {
		return bookDao.numOfOneBookDonation(isbn);
	}
	
	@Override
	public int numOfBookKMine(int memNo) {
		return bookDao.numOfMyBookKeeping(memNo);
	}

	@Override
	public int numOfBookDMine(int memNo) {
		return bookDao.numOfMyBookDonation(memNo);
	}

	@Override
	public int numOfBookK() {
		return bookDao.numOfBookKeeping();
	}

	@Override
	public int numOfBookD() {
		return bookDao.numOfBookDonation();
	}

	@Override
	public int numOfAllBooks() {
		return bookDao.numOfAllBooks();
	}

	@Override
	public int pointUpdateService(int memNo, int point) throws Exception {
		
		HashMap<String, Integer> pointUpMap = new HashMap<String, Integer>();
		pointUpMap.put("memNo", memNo);
		pointUpMap.put("point", point);
		
		return memberDao.pointUpdate(pointUpMap);
	}

	@Override
	public Book getBooksService(String isbn) {
		return bookDao.getBooks(isbn);
	}

	@Override
	public Collection<Book> getHighCateBook(String typeCode) {
		return bookDao.getHighCateBook(typeCode);
	}

	@Override
	public Collection<Book> getLowCateBook(String category) {
		return bookDao.getLowCateBook(category);
	}

}
