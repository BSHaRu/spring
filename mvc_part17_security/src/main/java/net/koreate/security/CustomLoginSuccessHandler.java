package net.koreate.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		System.out.println("login success");
		UserDetails user = (UserDetails) auth.getPrincipal();
		System.out.println(user);
		
		List<String> roleNames = new ArrayList<>();
		
		for(GrantedAuthority a : auth.getAuthorities()) {
			System.out.println("보유 권한 : " + a.getAuthority());
			roleNames.add(a.getAuthority());
		}
		
		String path = request.getContextPath();
		if(roleNames.contains("ROLE_ADMIN")) {
			System.out.println("관리자 권한");
			response.sendRedirect(path + "/test/master");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			System.out.println("회원 권한");
			response.sendRedirect(path + "/test/test");
			return;
		}
		
		response.sendRedirect(path);
		System.out.println("권한 없음");
	}

}
