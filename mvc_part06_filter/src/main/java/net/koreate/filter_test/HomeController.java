package net.koreate.filter_test;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "home";
	}
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public void write() {
		
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(
//			String id, String pw, String name
			HttpServletRequest request,
			MemberVO member
			) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		
		System.out.println(member);
		
		return "redirect:/";
	}
}
