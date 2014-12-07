package com.libridge.service;

import java.util.Collection;

import com.libridge.vo.Book;
import com.libridge.vo.Member;

public interface BookService {
	
	int addBookService(Book book) throws Exception;
	
	Collection<Book> getMainBook(Member member) throws Exception;
	Collection<Book> getDbSearch(String keyword) throws Exception;
	Collection<Book> getHighCateBook(String typeCode);
	Collection<Book> getLowCateBook(String category);
	
	Book getBookService(String isbn) throws Exception;
	Book getBooksService(String isbn);
	
	Collection<Member> getMembers(int memberNumber) throws Exception;
	
	int pointUpdateService(int memNo, int point) throws Exception;
	
	int numOfOneBookK(String isbn) throws Exception;
	int numOfOneBookD(String isbn) throws Exception;
	
	int numOfBookKMine(int memNo) throws Exception;
	int numOfBookDMine(int memNo) throws Exception;
	
	int numOfBookK() throws Exception;
	int numOfBookD() throws Exception;
	int numOfAllBooks() throws Exception;
	
}
