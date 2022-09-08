package net.koreate.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class CheckTokenInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session 발급한 token이랑 request token 비교
		if(request.getMethod().equalsIgnoreCase("POST")) {
			HttpSession session = request.getSession();
			String sessionToken = (String)session.getAttribute("csrf_token");
			System.out.println(sessionToken);
			
			String requestToken = request.getParameter("csrf_token");
			System.out.println(requestToken);
			
			if(requestToken == null 
					|| requestToken.equals("")
					|| !requestToken.equals(sessionToken)) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert('잘못된 접근입니다.');");
				pw.print("history.back();");
				pw.print("</script>");
				return false;
			}
		} // if end
		
		return true;
	}

	
	
}
