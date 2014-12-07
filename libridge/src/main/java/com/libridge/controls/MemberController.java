package com.libridge.controls;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.libridge.service.ApplyPageService;
import com.libridge.service.MemberService;
import com.libridge.vo.AjaxResult;
import com.libridge.vo.Member;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller("memberControllerAjax")
@SessionAttributes("member")
@RequestMapping("ajax/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ApplyPageService applyPageService;
	
	@RequestMapping(value="/add", produces="application/json")
	public void add(
			HttpSession session,
			Member member,
			ModelMap model) throws Exception {
//		Member member = new Member();
		session.setAttribute("member",member);
		int count = memberService.registerMember(member);
		//Member memberInfo = (Member)session.getAttribute("member");
		System.out.println("회원가입 프로세스 진행중...");
		if (count < 1) {
			throw new Exception("등록 실패입니다.");
		}
		model.addAttribute("member",member);
		model.addAttribute("status", AjaxResult.SUCCESS);
	}
	
	@RequestMapping(value="/detail", produces="application/json")
	public void detail(@ModelAttribute("member") Member member,
			ModelMap model) throws Exception {
		
		System.out.println(member);
		String str = member.getId();
		System.out.println(str);
		
		model.addAttribute("status", AjaxResult.SUCCESS);
		model.addAttribute("member", memberService.getMember(str));
	}
	

	//admin 계정에서나 사용하는 메소드. 회원 전체의 list를 보여준다.
	@RequestMapping("/list")
	public String list(
			@RequestParam(defaultValue="1") int pageNo, ModelMap model) throws Exception {
		int pageSize = 3;
		
		model.addAttribute("list", 
				memberService.getMemberList(pageNo, pageSize));
		model.addAttribute("pageNo", pageNo);
		
		int recordCount = memberService.countMember();
		int pageCount = recordCount / pageSize;
		if ((recordCount % pageSize) > 0)
			pageCount++;
		
		model.addAttribute("pageCount", pageCount);
		
		return "member/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, produces="application/json")
	public void update(@ModelAttribute("member") Member member,
			ModelMap model) throws Exception {
		
		System.out.println("회원정보 수정 프로세스 진행중...");
		
		int count = memberService.changeMember(member);
		
		if (count < 1) {
			throw new Exception("해당 멤버가 없습니다.");
		}
		
		model.addAttribute("member",member);
		System.out.println(member);
		model.addAttribute("status", AjaxResult.SUCCESS);
	}

	@RequestMapping(value="/imgUpload", method=RequestMethod.POST, produces="application/json")
	public void imgUpload(@ModelAttribute("member") Member member,
			String imgSrc, // base64 해당 파일명
			ModelMap model
			)throws Exception{
		// String[] imgFileSplit = imgFile.split("."); // .jpg 스플릿
		// String[] imgNameSplit = imgName.split("\\\\");  /으로 스플릿 할때 사용
		String[] imgSrcSplit = imgSrc.split(",");
		
		String photoName = member.getId()+"."+"jpg";  /*imgFileSplit[imgFileSplit.length-1];*/
		member.setPhotoUrl(photoName);
		
		
		System.out.println(photoName);
		//base64 객체 생성
		Base64 decoder = new Base64();
		//바이트 배열객체 생성후 받아 온 스트림값을 디코드 시켜 객체에 담는다.
		byte[] imgBytes = decoder.decode(imgSrcSplit[1]);
		// 아웃풋 스트림 객체로 파일 경로 및 파일 이름으로 위치를 지정
		
		FileOutputStream fos = new FileOutputStream(
				new File(
					"/home/magnet/devel/workspace/web/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/libridge_t/img/profileImg/"
						+ photoName));
		FileOutputStream fosBackup = new FileOutputStream(new File(
				"/home/magnet/devel/workspace/web/libridge_t/src/main/webapp/img/profileImg/" + photoName));
		//생성된 파일 객체에 디코드한 바이트 배열을 write한다
		fos.write(imgBytes);
		fosBackup.write(imgBytes);
		//아웃풋 스트림에 데이터를 확실하게 보내기 위해 flush
		fos.flush();
		fosBackup.flush();
		
		fos.close();
		fosBackup.close();
		
		
		memberService.imgUpdate(member);
				
	}

	//아이디 유무 판별
	@RequestMapping(value="/checkMember",method=RequestMethod.POST, produces="application/json")
	public void checkMember(@ModelAttribute("member") Member member,
			String recId, ModelMap model) throws Exception{
		
		System.out.println("MemberController.java -> checkMember진입");
		
		System.out.println("js에서 받은 값 recId : " + recId);
		
		ArrayList<Member> members = (ArrayList<Member>)memberService.getAllMemberId();
		
		for(int i=0; i< members.size(); i++){
			
			System.out.println("회원아이디"+members.get(i).getId());
		}
		
		for(int i=0; i< members.size(); i++){
			
			if(recId.equals(members.get(i).getId())){
				
				model.addAttribute("recId", recId);
				System.out.println(recId+"와 일치합니다"); 
				model.addAttribute("status", AjaxResult.SUCCESS);
				  
				break;
				  
			 }else{
				 model.addAttribute("status", AjaxResult.FAIL);
				 model.addAttribute("message", "가입가능한 아이디입니다");
				 System.out.println(recId+"과 일치하는 회원이 없습니다.");
				 System.out.println(recId+"는 가입가능한 아이디입니다.");
				 
			 }
		}
	}
	
	@RequestMapping(value="/idCheck",method=RequestMethod.POST, produces="application/json")
	public void idCheck(String requestId, ModelMap model)
			throws Exception{
		
		System.out.println("MemberController.java -> checkMember진입");
		
		System.out.println("js에서 받은 값 recId : " + requestId);
		
		ArrayList<Member> members = (ArrayList<Member>)memberService.getAllMemberId();
		
		for(int i=0; i< members.size(); i++){
			
			System.out.println("회원아이디"+members.get(i).getId());
		}
		
		for(int i=0; i< members.size(); i++){
			
			if(requestId.equals(members.get(i).getId())){
				
				System.out.println(requestId+"와 일치합니다"); 
				model.addAttribute("status", AjaxResult.FAIL);
				  
				break;
				  
			 }else{
				 model.addAttribute("status", AjaxResult.SUCCESS);
				 model.addAttribute("message", "가입가능한 아이디입니다");
				 System.out.println(requestId+"는 가입가능한 아이디입니다.");
				 
			 }
		}
	}
	
	@RequestMapping(value="/grantPoint", method=RequestMethod.POST, produces="application/json")
	public void grantPoint(ModelMap model, int toPoint, String toUser,
			@ModelAttribute Member member) throws Exception {
		
//		친구에게 포인트 추가
		System.out.println("toUser : " + toUser);
		System.out.println("mnemberController point " + toPoint);
		memberService.plusPoint(toPoint, toUser);
		
//		자신의 포인트 차감
		int myPoint = member.getPoint() - toPoint;
		System.out.println(member.getPoint());
		System.out.println(myPoint);
		memberService.minusPoint(myPoint, member.getMemNo());
		
		model.addAttribute("status", AjaxResult.SUCCESS);
		model.addAttribute("member", member);
	}
	
}
