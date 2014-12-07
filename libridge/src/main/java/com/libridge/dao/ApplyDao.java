package com.libridge.dao;

import java.util.Collection;

import com.libridge.vo.Lend;
import com.libridge.vo.Member;

public interface ApplyDao {
	Lend getBook(String isbn);
	Collection<Member> getMembers(int memberNumber);
}
