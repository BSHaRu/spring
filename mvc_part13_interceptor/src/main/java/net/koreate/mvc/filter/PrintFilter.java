package net.koreate.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrintFilter implements Filter{

	String filterParam;
	
	@Override	// 최초에 호출 될 때
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("PrintFilter init() 시작");
		
		filterParam = filterConfig.getInitParameter("filterParam");	
		
		System.out.println("PrintFilter init() 종료");
	}

	@Override	// 요청 할 떄마다 호출
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("PrintFilter doFilter() 시작");
		
		request.setCharacterEncoding(filterParam);
		chain.doFilter(request, response);	// 이거 없으면 filter에 아무값도 안보낸거랑 똑같다고함
		
		System.out.println("PrintFilter doFilter() 종료");
	}

	@Override
	public void destroy() {
		System.out.println("PrintFilter destroy()");
	}

}
