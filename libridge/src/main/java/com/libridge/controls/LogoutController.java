package com.libridge.controls;

import javax.servlet.http.HttpSession;

import com.libridge.service.MemberService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller("logoutControlAjax")
@RequestMapping("ajax/auth")
@SessionAttributes("member") 
public class LogoutController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/logout", 
			method=RequestMethod.POST,
			produces="application/json")
	public void logout(
			String id, String password,
			HttpSession session,
			ModelMap model)
			throws Exception {
		
		Member member = memberService.existsMember(id, password);
		
		if (member != null) {
			System.out.println("로그아웃 시도");
			
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", new Member());
			session.invalidate();
			
		} else {
			model.addAttribute("status", AjaxResult.FAIL);
			model.addAttribute("member", new Member());
			session.invalidate();
			/*
			Cookie cookie = new Cookie("email", "guest");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			*/
		}
	}
	
	@ExceptionHandler(Exception.class)
	public void handleError(Exception ex, ModelMap model) {
		model.addAttribute("error", ex.getMessage());
	}
}
