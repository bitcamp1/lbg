package com.libridge.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.libridge.service.ApplyService;
/*
@Controller("applyControllerAjax")
@RequestMapping("/ajax")
@SessionAttributes("book")
public class ApplyController {
	
	@Autowired
	ApplyService applyService;
	
	@RequestMapping(value="/apply", method=RequestMethod.GET, produces="application/json")
	public void applyBook (
		@RequestParam(defaultValue="9788970908489") int apply_no,
		ModelMap modelMap) throws Exception {
		
		modelMap.addAttribute("Lend", applyService.applyBookService(apply_no));
	}
}
*/