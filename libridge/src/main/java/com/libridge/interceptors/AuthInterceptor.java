package com.libridge.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.libridge.vo.Member;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		
		// 로그인 요청, 메인화면(mainContent), 신청페이지는 통과
		if (servletPath.startsWith("/auth") || 
				servletPath.startsWith("/ajax/auth") ||
				servletPath.startsWith("/ajax/book") ||
				servletPath.startsWith("/ajax/applyPage") ||
				servletPath.startsWith("/ajax/member/add") ||
				servletPath.startsWith("/ajax/member/idCheck")) {
			
			System.out.println(servletPath);
			System.out.println(contextPath);
			return super.preHandle(request, response, handler);
		}
		
		// 로그인 되어 있는 상태일 때는 통과
		Member member = (Member) request.getSession().getAttribute("member");
		if (member != null && member.getId() != null) {
			System.out.println("로그인 상태. 세션에 값이 있음");
			return super.preHandle(request, response, handler);
			
		}
		
		// 로그인 하도록 처리
//		if (servletPath.startsWith("/ajax")) { // AJAX 요청인 경우 
//			RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("/ajax/auth/loginFail.do");
//			dispatcher.forward(request, response);
//			
//		} else { // 일반 요청인 경우
//			response.sendRedirect(contextPath + "/auth/login.do");
//		}
		
		return false;
	}
}
