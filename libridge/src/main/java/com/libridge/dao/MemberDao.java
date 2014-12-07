package com.libridge.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Member;

public interface MemberDao {
	
	int addMember(Member member);
	Member getMember(String id);
	int updateMember(Member member);
	Collection<Member> getMemberList(HashMap<String,Integer> paramMap);
	Member existsMember(HashMap<String,String> paramMap);
	int size();
	Collection<Member> getMembers(int projectNo);
	
//	책 등록시 포인트 추가
	int pointUpdate(HashMap<String, Integer> PointUpMap);
	
//	책 신청시 포인트 차감
	int minusPoint(HashMap<String, Integer>paramMap);
	
//	신청 거부시 포인트 반환
	int retrievePoint(HashMap<String, Integer>paramMap);
	
//	포인트 양도시 친구에게 포인트 증가
	int grantPoint(HashMap<String, Object>paramMap);
	
	
	int imgUpload(Member member);
	ArrayList<Member> getAllMemberId();

}





