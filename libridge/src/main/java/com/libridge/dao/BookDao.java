package com.libridge.dao;

import java.util.Collection;

import com.libridge.vo.Book;
import com.libridge.vo.Member;

public interface BookDao {
	
	int addBook(Book book);
	
	Collection<Book> getMainBook(Member member);
	Collection<Book> getDbSearch(String keyword);
	Collection<Book> getHighCateBook(String typeCode);
	Collection<Book> getLowCateBook(String category);
	
	Book getBook(String isbn);
	Book getBooks(String isbn);
	Collection<Member> getMembers(int memberNumber);
	
	int numOfOneBookKeeping(String isbn);
	int numOfOneBookDonation(String isbn);
	
	int numOfMyBookKeeping(int memNo);
	int numOfMyBookDonation(int memNo);
	
	int numOfBookKeeping();
	int numOfBookDonation();
	int numOfAllBooks();
}
