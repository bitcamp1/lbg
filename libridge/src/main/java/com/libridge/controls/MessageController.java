package com.libridge.controls;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.MessageService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;

@Controller("messageControllerAjax")
@RequestMapping("ajax/message")
@SessionAttributes("member")
public class MessageController {
	
	@Autowired
	MessageService messageService;

	
	/* 메시지발송*/
	@RequestMapping(value="/sendMsg", method=RequestMethod.POST, produces="application/json")
	public void sendMessage(@ModelAttribute("member") Member member,
			String recId, String title, String content, ModelMap model)
		throws Exception{
		
		if (member != null) {
			
			HashMap<String, String> sendMsgMap = new HashMap<String, String>();
			sendMsgMap.put("sendId", member.getId());
			sendMsgMap.put("recId",recId);
			sendMsgMap.put("title",title);
			sendMsgMap.put("content",content);
			System.out.println("메세지 발송");
			System.out.println("    보내는 사람"+member.getId());
			System.out.println("    받는사람" + recId);
				
			model.addAttribute("sendMessage", messageService.sendMessage(sendMsgMap));
		
		} else {
			model.addAttribute("status", AjaxResult.FAIL);
		}
			
	}
	
	@RequestMapping(value="/sendSysMsg", method=RequestMethod.POST, produces="application/json")
	public void sendSysMsg(@ModelAttribute("member") Member member, String bookTitle,
			String recId, ModelMap model)
		throws Exception{
		
		if (member != null) {
			
			String title = "도서 신청 요청";
			String content = member.getId() + " 님께서 " + bookTitle +"을(를) 신청하셨습니다. 대여 페이지에서 확인해주세요.";
			
			System.out.println(content);
			
			
			HashMap<String, String> sendMsgMap = new HashMap<String, String>();
			
			sendMsgMap.put("recId",recId);
			sendMsgMap.put("title",title);
			sendMsgMap.put("content",content);
			System.out.println("메세지 발송");
			System.out.println("    보내는사람 : System");
			System.out.println("    받는사람" + recId);
				
			model.addAttribute("sendMessage", messageService.sendSysMessage(sendMsgMap));
		
		} else {
			model.addAttribute("status", AjaxResult.FAIL);
		}
			
	}
	
	/* 보낸 메시지 리스트*/
	@RequestMapping(value="/sendMsgList", method=RequestMethod.POST, produces="application/json")
	public void getSendMessageList(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo, ModelMap model)
			throws Exception {
		
		int pageSize = 10;
		
		String sendId= member.getId();
		
		System.out.println("MessageController.java -> getSendMessageList");
		
		if (member != null) {
		
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("sendMsgList", 
					messageService.getSendMessageList(sendId, pageNo, pageSize)) ;
			
			System.out.println("MessageController.java -> getSendMessageList : if문");
			
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize",pageSize);
			model.addAttribute("recordCount",messageService.countSendMessage(sendId));
		
		} 
		
	}
	
	//수신 메시지 리스트
	@RequestMapping(value="/recMsgList", method=RequestMethod.POST, produces="application/json")
	public void getRecMessageList(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo, ModelMap model)
			throws Exception {
		
		int pageSize = 10;
		
		String recId= member.getId();
		
		System.out.println("받는사람"+recId);
		
		if (member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("receivedMsgList", messageService.getRecMessageList(recId, pageNo, pageSize));
			
			System.out.println("MessageController.java -> getRecMessageList : if문");
			
			model.addAttribute("pageNo",pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", messageService.countRecMessage(recId));
			
			System.out.println("수신메시지리스트 전달");
			
		} 
	}
	
	//시스템 수신 메시지 리스트
	@RequestMapping(value="/sysRecMsgList", method=RequestMethod.POST, produces="application/json")
	public void getSysRecMessageList(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo, ModelMap model)
			throws Exception {
		
		int pageSize = 10;
		
		String recId= member.getId();
		
		System.out.println("받는사람"+recId);
		
		if (member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("sysReceivedMsgList", messageService.getSysRecMessageList(recId, pageNo, pageSize));
			
			System.out.println("MessageController.java -> getRecMessageList : if문");
			
			model.addAttribute("pageNo",pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", messageService.countRecMessage(recId));
			
			System.out.println("수신메시지리스트 전달");
			
		} 
	}
	
	
	// 메세지 내용 확인
	@RequestMapping(value="/msgContent", method=RequestMethod.POST, produces="application/json")
	public void getMsgContent(@ModelAttribute("member") Member member, ModelMap model, int msgNo)
			throws Exception {
		
		System.out.println("MessageController.java -> getMsgContent");
		
		if (member != null) {
		
			int count = messageService.unreadToReadMsg(msgNo);
			
			if (count > 0) {
				model.addAttribute("status", AjaxResult.SUCCESS);
				model.addAttribute("message", messageService.getMsgContent(msgNo));
				
				System.out.println("MessageController.java -> getMsgContent : if문");
				
			} else {
				model.addAttribute("status", AjaxResult.FAIL);
				model.addAttribute("error", "쪽지 가져오기 실패");
			}
			
		} 
		
	}
	
	// 시스템 메세지 내용 확인
	@RequestMapping(value="/sysMsgContent", method=RequestMethod.POST, produces="application/json")
	public void getSysMsgContent(@ModelAttribute("member") Member member, ModelMap model, int msgNo)
			throws Exception {
		
		System.out.println("MessageController.java -> getMsgContent");
		
		if (member != null) {
		
			int count = messageService.unreadToReadSysMsg(msgNo);
			
			if (count > 0) {
				model.addAttribute("status", AjaxResult.SUCCESS);
				model.addAttribute("message", messageService.getSysMsgContent(msgNo));
				
				System.out.println("MessageController.java -> getMsgContent : if문");
				
			} else {
				model.addAttribute("status", AjaxResult.FAIL);
				model.addAttribute("error", "쪽지 가져오기 실패");
			}
			
		} 
		
	}
		
}
