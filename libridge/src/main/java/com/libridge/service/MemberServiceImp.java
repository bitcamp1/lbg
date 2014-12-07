package com.libridge.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libridge.dao.MemberDao;
import com.libridge.vo.Member;


@Service
public class MemberServiceImp implements MemberService {
	@Autowired
	MemberDao memberDao;

	public int registerMember(Member member) throws Exception {
		 return memberDao.addMember(member);
	}

	public Collection<Member> getMemberList(int pageNo, int pageSize)
			throws Exception {
		HashMap<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("pageStartIndex", (pageNo-1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		return memberDao.getMemberList(paramMap);
	}

	public Member getMember(String id) throws Exception {
		return memberDao.getMember(id);
	}

	public int changeMember(Member member) throws Exception {
		return memberDao.updateMember(member);
	}

	public int countMember() throws Exception {
		return memberDao.size();
	}

	public Member existsMember(String id, String password) throws Exception {
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("id", id);
		paramMap.put("password", password);
		
		return memberDao.existsMember(paramMap);
	}
	
	@Override
	public int plusPoint(int point, int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("point", point);
		paramMap.put("memNo", memNo);
		
		return memberDao.retrievePoint(paramMap);
	}
	

	@Override
	public int plusPoint(int toPoint, String toUser) throws Exception {
		
		System.out.println("memberServiceImpl -> point : " + toPoint);
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("toPoint", toPoint);
		paramMap.put("toUser", toUser);
		
		return memberDao.grantPoint(paramMap);
	}

	@Override
	public int minusPoint(int point, int memNo) throws Exception {
		
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("point", point);
		paramMap.put("memNo", memNo);
		
		return memberDao.minusPoint(paramMap);
	}

	@Override
	public int imgUpdate(Member member) throws Exception {
		return memberDao.imgUpload(member);
	}

	@Override
	public ArrayList<Member> getAllMemberId() throws Exception {
		return memberDao.getAllMemberId();
	}

	@Override
	public int plusPoint() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

