package com.libridge.service;

import java.util.ArrayList;
import java.util.Collection;

import com.libridge.vo.Member;

public interface MemberService {
	
	int registerMember(Member member) throws Exception;
	Collection<Member> getMemberList(int pageNo, int pageSize) throws Exception;
	Member getMember(String id) throws Exception;
	int changeMember(Member member) throws Exception;
	int countMember() throws Exception;
	Member existsMember(String id, String password) throws Exception;
	
	int minusPoint(int point, int memNo) throws Exception;
	
	int plusPoint(int point, int memNo) throws Exception;
	
	int plusPoint(int point, String toUser) throws Exception;
	
	int plusPoint() throws Exception;
	
	int imgUpdate(Member member)throws Exception;
	ArrayList<Member> getAllMemberId()throws Exception;

}