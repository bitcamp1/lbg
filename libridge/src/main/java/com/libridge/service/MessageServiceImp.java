package com.libridge.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libridge.dao.MemberDao;
import com.libridge.dao.MessageDao;
import com.libridge.vo.Message;


@Service
public class MessageServiceImp implements MessageService {
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	MemberDao memberDao;
	
	
	@Override
	public int sendMessage(HashMap<String, String> sendMsgMap) throws Exception {
		return messageDao.sendMessage(sendMsgMap);
	}
	

	@Override
	public int sendSysMessage(HashMap<String, String> sendMsgMap) throws Exception {
		return messageDao.sendSysMsg(sendMsgMap);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int removeMessage(int MessageNo) throws Exception {
		/*MessageDao.deleteAllMember(MessageNo);
		return MessageDao.delete(MessageNo);*/
		return 0;
	}

	
	@Override
	public Collection<Message> getRecMessageList(String recId, int pageNo, int pageSize)
			throws Exception {
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recId", recId);
		paramMap.put("pageStartIndex", (pageNo-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		
		return messageDao.getRecMessageList(paramMap);
	}
	

	@Override
	public Collection<Message> getSysRecMessageList(String recId, int pageNo, int pageSize)
			throws Exception {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recId", recId);
		paramMap.put("pageStartIndex", (pageNo-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		
		return messageDao.getSysRecMessageList(paramMap);
	}
	

	@Override
	public Collection<Message> getSendMessageList(String sendId, int pageNo, int pageSize)
			throws Exception {
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sendId", sendId);
		paramMap.put("pageStartIndex", (pageNo-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		
		return messageDao.getSendMessageList(paramMap);
	}
	

	@Override
	public Message getMsgContent(int msgNo) throws Exception {
		return messageDao.getMsgContent(msgNo);
	}
	
	// 시스템 메세지 내용 확인
	@Override
	public Message getSysMsgContent(int msgNo) throws Exception {
		return messageDao.getSysMsgContent(msgNo);
	}

	@Override
	public int unreadToReadMsg(int msgNo) throws Exception {
		return messageDao.changeReadStatus(msgNo);
	}
	

	@Override
	public int unreadToReadSysMsg(int msgNo) throws Exception {
		return messageDao.sysChangeReadStatus(msgNo);
	}
	
	
	@Override
	public int countSendMessage(String sendId) throws Exception {
		return 	messageDao.sendSize(sendId);
	}
	

	@Override
	public int countRecMessage(String recId) throws Exception {
		return 	messageDao.recSize(recId);
	}


	@Override
	public int countUnreadMessage(String recId) throws Exception {
		return messageDao.unreadSize(recId);
	}


	@Override
	public int countUnreadSysMessage(String recId) throws Exception {
		return messageDao.unreadSysSize(recId);
	}


}

