package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import com.libridge.vo.Message;

public interface MessageService {
	
//	메세지 발송
	int sendMessage(HashMap<String, String> sendMsgMap) throws Exception;
	
//	시스템 메세지 발송
	int sendSysMessage(HashMap<String,String> sendMsgMap) throws Exception;
	
	Collection<Message> getSendMessageList(String sendId, int pageNo, int pageSize) throws Exception;
	Collection<Message> getRecMessageList(String recId, int pageNo, int pageSize) throws Exception;
	
//	받은 메세지함(시스템)
	Collection<Message> getSysRecMessageList(String recId, int pageNo, int pageSize) throws Exception;
	
//	메세지 내용
	Message getMsgContent(int msgNo) throws Exception;
	
//	읽은 메세지로 상태값 변경(일반 메세지 N->Y)
	int unreadToReadMsg(int msgNo) throws Exception;
	
//	읽은 메세지로 상태값 변경(시스템 메세지)
	int unreadToReadSysMsg(int msgNo) throws Exception;
	
	int removeMessage(int MessageNo) throws Exception;
	
	int countSendMessage(String sendId) throws Exception;
	int countRecMessage(String recId) throws Exception;
	
//	읽지 않은 메세지 개수
	int countUnreadMessage(String recId) throws Exception;
	
	int countUnreadSysMessage(String recId) throws Exception;
	
//	시스템 메세지 내용
	Message getSysMsgContent(int msgNo) throws Exception;

}
