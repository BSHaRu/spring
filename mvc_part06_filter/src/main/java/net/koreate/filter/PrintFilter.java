package net.koreate.filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
			@WebInitParam(name = "printDir", value = "/log"),
			@WebInitParam(name = "printFile", value="filterLog.log")
		})
public class PrintFilter implements Filter {
	
	String printDir;
	String printFile;
	
	PrintWriter pw;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("PrintFilter init() start");
		printDir = fConfig.getInitParameter("printDir");
		printFile = fConfig.getInitParameter("printFile");
		ServletContext apllication = fConfig.getServletContext();
		String path = apllication.getRealPath(printDir);
		System.out.println(path);
		
		File file = new File(path);
		if(!file.exists()) {
			System.out.println("폴더 생성");
			file.mkdirs();
		}
		if(pw == null) {
			try {
				file = new File(path,printFile);
				pw = new PrintWriter(new FileWriter(file,true),true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("PrintFilter init() end");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		pw.println("======================================");
		pw.printf("로그 시간 : %s %n", sdf.format(new Date()));
		pw.printf("요청 IP : %s %n", req.getRemoteAddr());
		pw.printf("요청 URL : %s %n", req.getRequestURI());
		pw.printf("전송 방식 : %s %n" , req.getMethod());
		pw.println("======================================");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

	

}
