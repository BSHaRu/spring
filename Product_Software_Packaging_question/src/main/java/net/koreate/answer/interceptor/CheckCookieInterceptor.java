package net.koreate.answer.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import net.koreate.user.service.UserService;
import net.koreate.user.vo.UserVO;

public class CheckCookieInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService us;

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse respsonse, 
			Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			return true;
		}
		
		Cookie cookie = WebUtils.getCookie(request, "signInCookie");
		if(cookie != null) {
			UserVO vo = us.getUserById(cookie.getValue());
			
			if(vo != null) {
				session.setAttribute("userInfo", vo);
			}
		}
		return true;
	}
}
