package net.koreate.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class CreateTokenInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String token = UUID.randomUUID().toString();
		System.out.println("게시물 상세보기 요청 token : " + token);
		session.setAttribute("csrf_token", token);
		
		return true;
	}

	
}
