package com.libridge.controls;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libridge.service.BookService;
import com.libridge.service.PersonalLibraryService;
import com.libridge.vo.Book;
import com.libridge.vo.Member;

@Controller("bookControllerAjax")
@RequestMapping("/ajax/book")
@SessionAttributes("member")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	PersonalLibraryService personalLibraryService;
	
	@RequestMapping(value="/bookInfo", method=RequestMethod.POST, produces="application/json")
	public void getBook (String isbn, ModelMap modelMap) throws Exception {
		
		modelMap.addAttribute("bookInfo", bookService.getBookService(isbn));
		modelMap.addAttribute("numOneBK", bookService.numOfOneBookK(isbn));
		modelMap.addAttribute("numOneBD", bookService.numOfOneBookD(isbn));
	}
	
	@RequestMapping(value="/numBook", method=RequestMethod.POST, produces="application/json")
	public void getNoOfBook (String isbn, HttpSession session, ModelMap modelMap) throws Exception {
		
		Member member = (Member)session.getAttribute("member");
		
		if (member == null || (member.getId() == null) || (member.getId() == "")) {
			modelMap.addAttribute("numOneBK", bookService.numOfOneBookK(isbn));
			modelMap.addAttribute("numOneBD", bookService.numOfOneBookD(isbn));
		} else {
			modelMap.addAttribute("numMyBK", bookService.numOfBookKMine(member.getMemNo()));
			modelMap.addAttribute("numMyBD", bookService.numOfBookDMine(member.getMemNo()));
		}
	}
	
	/* 책 등록 부분 */
	@RequestMapping(value="/addBook", method=RequestMethod.POST, produces="application/json")
	public void addBook(Book book, 
			String regCode, 
			String category, 
			int price, 
			String isbn,
			@ModelAttribute("member") Member member,
			ModelMap modelMap) throws Exception {
		
//		PersonalLibrary persLib = new PersonalLibrary();
		
		System.out.println(price);
		
		switch (category) {
			case "소설" : case "시/에세이" :
				book.setTypeCode("1");
				break;
				
			case "인문" : case "경제/경영" : 
			case "정치/사회" : case "종교" : 
			case "역사/문화" :
				book.setTypeCode("2");
				break;
				
			case "과학" : case "컴퓨터/IT" : 
			case "기술/공학" :
				book.setTypeCode("3");
				break;
				
			case "가정/생활" : case "요리" : 
			case "건강" : case "취미/스포츠" : 
			case "여행/기행" :
				book.setTypeCode("4");
				break;
				
			case "예술/대중문화" : case "만화" :
				book.setTypeCode("5");
				break;
				
			case "중/고등학습" : case "외국어" : 
			case "취업/수험서" : case "사전" : 
			case "어린이영어" : case "초등학습" : 
				book.setTypeCode("6");
				break;
				
			case "청소년" : case "어린이" :
			case "유아" : case "아동" :  
			case "어린이전집" : 
				book.setTypeCode("7");
				break;
				
			case "정부간행물" : case "잡지" : 
			case "자기계발" :
				book.setTypeCode("8");
				break;

		}
		
		System.out.println(book.getCategory());
		
		System.out.println("등록할 책의 장르구분코드 : " + book.getTypeCode());
		
		System.out.println("Reg_Code : " + regCode);
		
		Book dbBook = bookService.getBookService(book.getIsbn());
		
		if(dbBook == null) {
			
			System.out.println("control의 if문 안에까지 왔음. 기등록된 책이 아님. 책을 먼저 등록하고 개인도서목록을 등록");
			
			bookService.addBookService(book);
			
			personalLibraryService.addMyBookInfo(member.getMemNo(), regCode, isbn);
			
			if(regCode.equals("2")) {
				System.out.println("Reg_Code가 2입니다. DONATION 테이블에 정보를 넣어야합니다.");
				personalLibraryService.addDonation(member.getMemNo());
			}
			
			int point = price / 100;
			
			bookService.pointUpdateService(member.getMemNo(), point);
			
		} else {
			
			System.out.println("control의 else문 안에까지 왔음. 이미 등록된 책. 개인도서 목록에만 db작성");
			
			personalLibraryService.addMyBookInfo(member.getMemNo(), regCode, isbn);
			
			if(regCode.equals("2")) {
				System.out.println("Reg_Code가 2입니다. DONATION 테이블에 정보를 넣어야합니다.");
				personalLibraryService.addDonation(member.getMemNo());
			}
			
			int point = price / 100;
			
			bookService.pointUpdateService(member.getMemNo(), point);
		} 
	}
	
	@RequestMapping(value="/listBook", method=RequestMethod.POST, produces="application/json")
	public void listBook(Book book, 
			@ModelAttribute("member") Member member,
			ModelMap modelMap) throws Exception {
	}
	
	@RequestMapping(value="/mainBook", method=RequestMethod.POST, produces="application/json")
	public void mainBook(HttpSession session,
			ModelMap modelMap) throws Exception {
		
		Member member = (Member)session.getAttribute("member");
		
//		int memNo = 0;
//		
//		if (member != null) {
//			memNo = member.getMemNo();
//		} else {
//			System.out.println("세션에 멤버 정보가 없을 때 memNo=0");
//			memNo = 0;
//		}
//		
//		modelMap.addAttribute("mainBook", bookService.getMainBook(memNo));
		
		modelMap.addAttribute("mainBook", bookService.getMainBook(member));
		
	}
	
	@RequestMapping(value="/dbSearch", method=RequestMethod.POST, produces="application/json")
	public void dbSearch(String keyword,
			ModelMap modelMap) throws Exception {
		
		keyword = "%" + keyword + "%";
		
		System.out.println(keyword);
		
		System.out.println("BookController.java / dbSearch");
		modelMap.addAttribute("searchR", bookService.getDbSearch(keyword));
		
	}
	
	@RequestMapping(value="/highCate", method=RequestMethod.POST, produces="application/json")
	public void highCate(String typeCode,
			ModelMap modelMap) throws Exception {
		
		System.out.println(typeCode);
		
		System.out.println("BookController.java / HighCategory");
		modelMap.addAttribute("highCate", bookService.getHighCateBook(typeCode));
		
	}
	
	@RequestMapping(value="/lowCate", method=RequestMethod.POST, produces="application/json")
	public void lowCate(String category,
			ModelMap modelMap) throws Exception {
		
		System.out.println(category);
		
		System.out.println("BookController.java / lowCategory");
		modelMap.addAttribute("lowCate", bookService.getLowCateBook(category));
		
	}
}
