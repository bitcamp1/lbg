package com.libridge.controls;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.AcceptService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;

@Controller("acceptControllerAjax")
@RequestMapping("/ajax/accept")
@SessionAttributes("member")
public class AcceptController {
	
	@Autowired
	AcceptService acceptService;
	
/*--Keeping Table------------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping(value="/acceptList", produces="application/json")
	public void acceptList(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="") int pageNo,
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member!= null) {
		model.addAttribute("status",  AjaxResult.SUCCESS);
		model.addAttribute("member", member);
		model.addAttribute("acceptList", acceptService.getAcceptList(pageNo, pageSize, member.getMemNo()));
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("recordCount", acceptService.countAccept(member.getMemNo()));
		System.out.println("acceptController->acceptList");
		}
	}
		
		
	@RequestMapping(value="/lendDeliveryList", produces="application/json")
	public void lendDeliveryList(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo,
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member!= null) {
		model.addAttribute("status",  AjaxResult.SUCCESS);
		model.addAttribute("member", member);
		model.addAttribute("lendDeliveryList", acceptService.getLendDeliveryList(pageNo, pageSize, member.getMemNo()));
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("recordCount", acceptService.countLendDelivery(member.getMemNo()));
		System.out.println("acceptController->lendDeliveryList");
		}
	}
			
	@RequestMapping(value="/lendList", produces="application/json")
	public void lendList(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo,
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member!= null) {
		model.addAttribute("status",  AjaxResult.SUCCESS);
		model.addAttribute("member", member);
		model.addAttribute("lendList", acceptService.getLendList(pageNo, pageSize, member.getMemNo()));
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("recordCount", acceptService.countLend(member.getMemNo()));
		System.out.println("acceptController->lendList");
		}

	}
	
	@RequestMapping(value="/deliveryConfirm", produces="application/json")
	public void deliveryConfirm(@RequestParam(defaultValue="") int applyNo,
			ModelMap model) throws Exception {
		
		System.out.println("myPage.js -> AcceptController.java / deliveryConfirm");
		
		int count = acceptService.deliveryConfirm(applyNo);
		
		if(count<1) {
			throw new Exception("해당 배송 번호가 없습니다");
		}
		
		model.addAttribute("status", AjaxResult.SUCCESS);
	}
	
	@RequestMapping(value="/returnComplConfirm", produces="application/json")
	public void returnComplConfirm(@RequestParam(defaultValue="") int applyNo,
			ModelMap model) throws Exception {
		
		System.out.println("myPage.js -> AcceptController.java / returnComplConfirm");
		
		int count1 = acceptService.returnComplete(applyNo);
		int count2 = acceptService.rentalComplete(applyNo);
		int count3 = acceptService.rentalStatusY(applyNo);
		
		if( (count1 < 1) || (count2 < 1) || (count3 < 1)) {
			throw new Exception("해당 배송 번호가 없습니다");
		}
		
		model.addAttribute("status", AjaxResult.SUCCESS);
	}
	
	
/*--Donation Table------------------------------------------------------------------------------------------------------------*/

	@RequestMapping(value="/acceptListD", produces="application/json")
	public void acceptListD(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="") int pageNo,
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member!= null) {
		model.addAttribute("status",  AjaxResult.SUCCESS);
		model.addAttribute("member", member);
		model.addAttribute("acceptList", acceptService.getAcceptListD(pageNo, pageSize, member.getMemNo()));
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("recordCount", acceptService.countAcceptD(member.getMemNo()));
		System.out.println("acceptController->acceptListD");
		}
	}
		
		
	@RequestMapping(value="/lendDeliveryListD", produces="application/json")
	public void lendDeliveryListD(@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo,
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member!= null) {
		model.addAttribute("status",  AjaxResult.SUCCESS);
		model.addAttribute("member", member);
		model.addAttribute("lendDeliveryList", acceptService.getLendDeliveryListD(pageNo, pageSize, member.getMemNo()));
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("recordCount", acceptService.countLendDeliveryD(member.getMemNo()));
		System.out.println("acceptController->lendDeliveryListD");
		}
	}

	
	@RequestMapping(value="/deliveryConfirmD", produces="application/json")
	public void deliveryConfirmD(@RequestParam(defaultValue="") int applyNo,
			ModelMap model) throws Exception {
		
		System.out.println("myPage.js -> AcceptController.java / deliveryConfirmD");
		
		int count = acceptService.deliveryConfirmD(applyNo);
		
		if(count<1) {
			throw new Exception("해당 배송 번호가 없습니다");
		}
		
		model.addAttribute("status", AjaxResult.SUCCESS);
	}

	
/*--------------------------------------------------------------------------------------------------------------------------*/

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(
				java.util.Date.class, 
				new CustomDateEditor(df, true));
	}
	
	@ExceptionHandler(Exception.class)
	public void handleError(Exception ex, ModelMap model) {
		model.addAttribute("error", ex.getMessage());
	}
	
}