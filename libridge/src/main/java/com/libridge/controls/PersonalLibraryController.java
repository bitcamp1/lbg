package com.libridge.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.PersonalLibraryService;
import com.libridge.vo.Member;

@Controller("PersonalLibraryController")
@RequestMapping("ajax/personalLibrary")
@SessionAttributes("member")
public class PersonalLibraryController {
	
	@Autowired
	PersonalLibraryService personalLibraryService;
	
	/* keeping, donation 책 정보를 가져오는 부분*/
	@RequestMapping(value="/myLibrary",method=RequestMethod.POST, produces="application/json")
	public void myLibrary(@ModelAttribute("member") Member userInfo, 
			String regCode, 
			@RequestParam(defaultValue="1") int pageNo, 
			ModelMap modelMap) throws Exception{
		
		int pageSize = 20;
		
		modelMap.addAttribute("myLibrary", personalLibraryService.myLibraryBookService(pageNo, pageSize, userInfo.getMemNo(), regCode));
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
		modelMap.addAttribute("recordCount", personalLibraryService.bookCountService(regCode));
		modelMap.addAttribute("member", userInfo);
	}
	
	/* 내서재에 들어왔을때 보여지는 부분 */
	@RequestMapping(value="/myLibraryAll", method=RequestMethod.POST, produces="application/json")
	public void myLibrary(@ModelAttribute("member") Member userInfo, 
			@RequestParam(defaultValue="1") int pageNo, ModelMap modelMap) throws Exception {
		int pageSize = 20;
		
		System.out.println("myLibraryAll");
		modelMap.addAttribute("myLibraryAll", personalLibraryService.myLibraryBookAllService(pageNo, pageSize, userInfo.getMemNo()));
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
		modelMap.addAttribute("recordCount", personalLibraryService.bookAllCountService());
		modelMap.addAttribute("member", userInfo);
	}
	
	/* 맵 구현부 부분 */
	@RequestMapping(value="/donationMap", method=RequestMethod.POST, produces="application/json")
	public void donation(@ModelAttribute("member") Member userInfo, int persBookNo, 
			@RequestParam(defaultValue="1") int pageNo, ModelMap modelMap) throws Exception {
		int pageSize = 10;
		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
		modelMap.addAttribute("donation", personalLibraryService.donationInfoService(pageNo, pageSize, persBookNo));
	}
	
}