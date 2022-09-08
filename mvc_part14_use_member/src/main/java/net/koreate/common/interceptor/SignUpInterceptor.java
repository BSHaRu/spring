package net.koreate.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
import net.koreate.user.service.UserService;
import net.koreate.user.vo.UserVO;

@Slf4j
public class SignUpInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService us;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, 
				Object handler)
				throws Exception {
		log.info("----- signUp preHandle Start");
		String upw = request.getParameter("upw");
		String repw = request.getParameter("repw");
		
		RequestDispatcher rd
			= request.getRequestDispatcher("/user/signUp");
		String message = "";
		if(!upw.equals(repw)) {
			message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		
		// 회원 가입 할 때 중복 아이디로 가입 할 경우 
		String uid = request.getParameter("uid");
		UserVO userInfo = us.getUserById(uid);	// 유저 id로 사용자 정보 검색
		if(userInfo != null) {
			message = uid+"는 사용 할 수 없는 아이디 입니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		
		log.info("----- signUp preHandle End");
		return true;
	}

	
}
