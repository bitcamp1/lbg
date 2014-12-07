package com.libridge.controls;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.ApplyPageService;
import com.libridge.service.MemberService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;


@Controller("applyControllerAjax")
@RequestMapping("/ajax/applyPage")
@SessionAttributes("member")
public class ApplyPageController {
	
	@Autowired
	ApplyPageService applyPageService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/applyOwner", method=RequestMethod.POST, produces="application/json")
	public void applyOwner (int pageNo, String isbn,
		HttpSession session,
		ModelMap modelMap) throws Exception {
		
		Member member = (Member)session.getAttribute("member");
		System.out.println("applyOwner.do");
		
		int pageSize = 2;
		
		if (member == null || (member.getId() == null) || (member.getId() == "")) {
			System.out.println("before Login");
			modelMap.addAttribute("status", AjaxResult.FAIL);
			modelMap.addAttribute("owner", applyPageService.getBookLocationService(isbn, pageNo, pageSize));
			modelMap.addAttribute("pageNo", pageNo);
			modelMap.addAttribute("pageSize", pageSize);
			modelMap.addAttribute("recordCount", applyPageService.pbnPagingSize(isbn));
			modelMap.addAttribute("error", "책을 대여하시려면 회원가입 또는 로그인을 해주세요");
		} else {
			int memNo = member.getMemNo();
			modelMap.addAttribute("status", AjaxResult.SUCCESS);
			modelMap.addAttribute("owner", applyPageService.getOwnerService(isbn, memNo, pageNo, pageSize));
			modelMap.addAttribute("rentalCount", applyPageService.rentalCount(member.getMemNo()));
			modelMap.addAttribute("pageNo", pageNo);
			modelMap.addAttribute("pageSize", pageSize);
			modelMap.addAttribute("recordCount", applyPageService.countBookOwner(isbn, member.getMemNo()));
			modelMap.addAttribute("member",member);
		}
	}
	
	@RequestMapping(value="/applyBook", method=RequestMethod.POST, produces="application/json")
	public void applyBook (
		@RequestParam(defaultValue="") int persBookNo, int point,
		@ModelAttribute("member") Member member,
		ModelMap modelMap) throws Exception {
		
		System.out.println("ApplyController / applyBook까지 들어옴");
		
		System.out.println("책 신청시 포인트 차감!!!!!!!");
		System.out.println("before point : " + point);
		point = point - 100;
		member.setPoint(point);
		System.out.println(member.getPoint());
		System.out.println("after point : " + point);
		
		int count = memberService.minusPoint(point, member.getMemNo());
		
		System.out.println(count);
		
		if (count < 1) {
			
			modelMap.addAttribute("status", AjaxResult.FAIL);
			modelMap.addAttribute("error", "포인트 차감에 실패하여 책 신청 실패.");
			
		} else {
			
			applyPageService.addApplyService(persBookNo, member.getMemNo());
			
			modelMap.addAttribute("status", AjaxResult.SUCCESS);
		}
	}
}
