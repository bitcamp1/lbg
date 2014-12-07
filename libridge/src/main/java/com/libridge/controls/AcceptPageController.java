package com.libridge.controls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.AcceptPageService;
import com.libridge.service.MemberService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;


@Controller("acceptPageControllerAjax")
@RequestMapping("/ajax/acceptPage")
@SessionAttributes("member")
public class AcceptPageController {
	
	@Autowired
	AcceptPageService acceptPageService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/applicantInfo", method=RequestMethod.GET, produces="application/json")
	public void applicantInfo (int applyNo,
		@ModelAttribute("member") Member member,
		ModelMap modelMap) throws Exception {
		
		if (member != null) {
			
			modelMap.addAttribute("status", AjaxResult.SUCCESS);
			modelMap.addAttribute("applicant", acceptPageService.getApplicantInfo(applyNo));
			modelMap.addAttribute("member",member);
			
		} else {
			modelMap.addAttribute("status", AjaxResult.FAIL);
			modelMap.addAttribute("error", "신청 회원 정보 가져오기 실패 or 로그인 풀림 or 로그인 안함");
		}
	}
	
	@RequestMapping(value="/applicantConfirm", method=RequestMethod.POST, produces="application/json")
	public void applicantConfirm (int applyNo,
		ModelMap modelMap) throws Exception {
		
		System.out.println("AcceptPageController / applicantConfirm까지 들어옴");
		
		acceptPageService.applicantConfirm(applyNo);
		acceptPageService.deliveryReadyStateCode(applyNo);
		acceptPageService.rentalStatusN(applyNo);
		
		modelMap.addAttribute("status", AjaxResult.SUCCESS);
	}
	
	@RequestMapping(value="/applicantDeny", method=RequestMethod.POST, produces="application/json")
	public void applicantDeny (int applyNo, int memNo, int point,
		ModelMap modelMap) throws Exception {
		
		System.out.println("AcceptPageController / applicantDeny까지 들어옴");
		System.out.println("신청 거부시 포인트 반환!!!!!!!");
		System.out.println("before point : " + point);
		point = point + 100;
		System.out.println("after point : " + point);
		
		int count = memberService.plusPoint(point, memNo);
		
		if (count < 1) {
			
			modelMap.addAttribute("status", AjaxResult.FAIL);
			modelMap.addAttribute("error", "포인트 반환에 실패하여 책 신청 실패.");
			
		} else {
			
			acceptPageService.applicantDeny(applyNo);
			modelMap.addAttribute("status", AjaxResult.SUCCESS);
		}
	}
	
}
