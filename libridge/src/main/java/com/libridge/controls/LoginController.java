package com.libridge.controls;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.MemberService;
import com.libridge.service.MessageService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;


@Controller("loginControlAjax")
@RequestMapping("/ajax/auth")
@SessionAttributes("member") // ModelMap에 저장된 객체 중에서 "member"는 세션에 저장하라.
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST,
			produces="application/json")
	public void login(
			String id, String password,
			HttpSession session,
			ModelMap model)
					throws Exception {
		
		System.out.println("loginController.java");
		
		Member member = memberService.existsMember(id, password);
		
		if (member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
			
		} else {
			model.addAttribute("status", AjaxResult.FAIL);
			model.addAttribute("member", new Member());
			session.invalidate();
		}
	}
	
	@RequestMapping(value="/loginFail", produces="application/json")
	public void loginStatus(ModelMap model)
		throws Exception {
		
		model.addAttribute("status", AjaxResult.FAIL);
	}
	
	@RequestMapping(value="/userInfo", produces="application/json")
	public void userInfo(
			HttpSession session,
			ModelMap model) 
			throws Exception {
		
		Member member = (Member)session.getAttribute("member");
		
		if (member == null || (member.getId() == null) || (member.getId() == "")) {
			model.addAttribute("status", AjaxResult.FAIL);
			model.addAttribute("error", "로그인 되지 않았습니다.");
		} else {
			
			int count1 = messageService.countUnreadMessage(member.getId());
			int count2 = messageService.countUnreadSysMessage(member.getId());
			
			int count = count1 + count2;
			
			System.out.println(count);
			
			if(count > 0) {
				model.addAttribute("count1", count1);
				model.addAttribute("count2", count2);
				
			} else {
				model.addAttribute("count", 0);
			}
			
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
		}
		
		//response.setHeader("Access-Control-Allow-Origin", "*");
	}
	
	@RequestMapping(value="/logout", produces="application/json")
	public void logout(HttpSession session, ModelMap model, @ModelAttribute("member") Member member) 
		throws Exception {
		
		if(member != null){
			model.addAttribute("status", AjaxResult.SUCCESS);
			session.invalidate();
			model.addAttribute("member", new Member());
		}
	}
	
	@RequestMapping(value="/refreshLogin", method=RequestMethod.POST,
			produces="application/json")
	public void refreshLogin(@ModelAttribute("member") Member member,
			ModelMap model)
			throws Exception {
		
		System.out.println("loginController.java / refreshLogin");
		
		if (member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
		} else {
			model.addAttribute("status", AjaxResult.FAIL);
		}
	}

	
	@ExceptionHandler(Exception.class)
	public void handleError(Exception ex, ModelMap model) {
		model.addAttribute("error", ex.getMessage());
	}
}






