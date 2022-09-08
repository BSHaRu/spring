package net.koreate.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		System.out.println("init encoding : "+encoding);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("encodingFilter 호출");
		// 전처리
		HttpServletRequest req = (HttpServletRequest)request;
		String method = req.getMethod().toUpperCase();
		if(method.equals("POST")) {
			request.setCharacterEncoding("UTF-8");
		}
		chain.doFilter(request, response);
		// 후처리
		System.out.println("encodingFilter 종료");
	}
	
	public void destroy() {
	}
}
