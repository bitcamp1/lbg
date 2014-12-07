package com.libridge.dao;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Message;

public interface MessageDao {
	
//	메세지 발송
	int sendMessage(HashMap<String,String> sendMsgMap);
	
//	시스템 메세지 발송
	int sendSysMsg(HashMap<String,String> sendMsgMap);
	
//	보낸 메세지함 리스트
	Collection<Message> getSendMessageList(HashMap<String, Object> paramMap);
	
//	받은 메세지함 리스트
	Collection<Message> getRecMessageList(HashMap<String, Object> paramMap);
	
//	받은 메세지함 리스트(시스템)
	Collection<Message> getSysRecMessageList(HashMap<String, Object> paramMap);
	
//	메시지 내용
	Message getMsgContent(int msgNo);
	
//	시스템 메시지 내용
	Message getSysMsgContent(int msgNo);
	
//	읽은 메세지 상태값 변경(일반쪽지)
	int changeReadStatus(int msgNo);
	
//	읽은 메세지 상태값 변경(시스템)
	int sysChangeReadStatus(int msgNo);
	
	int recSize(String recId);
	
	int sendSize(String sendId);
	
//	unread 메세지 숫자
	int unreadSize(String recId);
	
	int unreadSysSize(String recId);
	
}
