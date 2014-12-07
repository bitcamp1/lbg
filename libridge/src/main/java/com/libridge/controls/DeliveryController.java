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


import com.libridge.service.DeliveryService;
import com.libridge.service.PersonalLibraryService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;


@Controller("deliveryControllerAjax")
@RequestMapping("/ajax/delivery")
@SessionAttributes("member")
public class DeliveryController {
	
	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	PersonalLibraryService personalLibraryService;

	
/*--Keeping Table------------------------------------------------------------------------------------------------------------*/

	@RequestMapping(value="/applyList", method=RequestMethod.GET, produces="application/json")
	public void applyList(@ModelAttribute("member") Member member, 
			@RequestParam(defaultValue="1") int pageNo, 
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member != null){
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
			model.addAttribute("applyList", deliveryService.getApplyList(pageNo, pageSize, member.getMemNo()));
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", deliveryService.countApply(member.getMemNo()));
			
			System.out.println("--99--");
		
		}
	}
	
	
	@RequestMapping(value="/borrowDeliveryList", method=RequestMethod.GET, produces="application/json")
	public void borrowDeliveryList(
			@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo, ModelMap model) throws Exception {
		int pageSize = 5;
		
		if (member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			System.out.println("55");
			model.addAttribute("member", member);
			model.addAttribute("borrowDeliveryList", deliveryService.getDeliveryList(pageNo, pageSize, member.getMemNo()));
			model.addAttribute("pageNo", pageNo);
			System.out.println("66");
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", deliveryService.countDelivery(member.getMemNo()));
		}
	}
	
	@RequestMapping(value="/borrowList", method=RequestMethod.GET, produces="application/json")
	public void borrowList(@ModelAttribute("member") Member member, 
			@RequestParam(defaultValue="1") int pageNo, 
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		if(member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
			model.addAttribute("borrowList", deliveryService.getBorrowList(pageNo, pageSize, member.getMemNo()));;
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", deliveryService.countBorrow(member.getMemNo()));
		}
	}
	
	@RequestMapping(value="/receiveDone", method=RequestMethod.GET, produces="application/json")
	public void receiveDone(int delNo, ModelMap model) throws Exception {
		
		System.out.println("bookApplyConditioniD.js -> DeliveryController.java / ");
		
		int count = deliveryService.receiveDone(delNo);
		
		if(count<1) {
			throw new Exception("해당 배송 번호가 없습니다");
		}
		
		model.addAttribute("status", AjaxResult.SUCCESS);
		}
	
	@RequestMapping(value="/returnApply", method=RequestMethod.GET, produces="application/json")
	public void returnApply(int delNo, ModelMap model) throws Exception {
		
		System.out.println("myPage.js -> DeliveryController.java / ");
		
		int count = deliveryService.returnApply(delNo);
		
		if(count<1) {
			throw new Exception("해당 배송 번호가 없습니다");
		}
		
		model.addAttribute("status", AjaxResult.SUCCESS);
		}


/*--Donation Table------------------------------------------------------------------------------------------------------------*/
	
	@RequestMapping(value="/applyListD", method=RequestMethod.GET, produces="application/json")
	public void applyListD(@ModelAttribute("member") Member member, 
			@RequestParam(defaultValue="1") int pageNo, 
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member != null){
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
			model.addAttribute("applyList", deliveryService.getApplyListD(pageNo, pageSize, member.getMemNo()));
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", deliveryService.countApplyD(member.getMemNo()));
			
			System.out.println("applyListD.do");
		
		}
	}
	
	
	@RequestMapping(value="/borrowDeliveryListD", method=RequestMethod.GET, produces="application/json")
	public void borrowDeliveryListD(
			@ModelAttribute("member") Member member,
			@RequestParam(defaultValue="1") int pageNo, ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if (member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
			model.addAttribute("borrowDeliveryList", deliveryService.getDeliveryListD(pageNo, pageSize, member.getMemNo()));
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", deliveryService.countDeliveryD(member.getMemNo()));
			
			System.out.println("borrowDeliveryListD.do");
		}
	}
	
	
	@RequestMapping(value="/borrowListD", method=RequestMethod.GET, produces="application/json")
	public void borrowListD(@ModelAttribute("member") Member member, 
			@RequestParam(defaultValue="1") int pageNo, 
			ModelMap model) throws Exception {
		
		int pageSize = 5;
		
		if(member != null) {
			model.addAttribute("status", AjaxResult.SUCCESS);
			model.addAttribute("member", member);
			model.addAttribute("borrowList", deliveryService.getBorrowListD(pageNo, pageSize, member.getMemNo()));;
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("recordCount", deliveryService.countBorrowD(member.getMemNo()));
			
			System.out.println("borrowListD.do");
		}
	}
	
	
	@RequestMapping(value="/receiveDoneD", method=RequestMethod.POST, produces="application/json")
	public void receiveDoneD(int delNo, String isbn,
			@ModelAttribute("member") Member member,
			ModelMap model) throws Exception {
		
		System.out.println("bookApplyConditioniD.js -> DeliveryController.java -> receiveDoneD.do");
		System.out.println(delNo);
		System.out.println(isbn);
		
		
		int count = deliveryService.receiveDoneD(delNo);
		
		if(count<1) {
			throw new Exception("해당 배송 번호가 없습니다");
		} else {
			personalLibraryService.donatedBookAdd(member.getMemNo(), isbn, delNo);
			model.addAttribute("status", AjaxResult.SUCCESS);
		}
		
	}
	
	@RequestMapping(value="/readCompleteD", method=RequestMethod.POST, produces="application/json")
	public void readCompleteD(int delNo, int persBookNo, ModelMap model) throws Exception {
		
		System.out.println("myPage.js -> DeliveryController.java -> readCompleteD.do ");
		
		int count1 = deliveryService.readCompleteD(delNo);
		int count2 = deliveryService.rentalStatusY(persBookNo);
		
		if(count1<1) {
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
