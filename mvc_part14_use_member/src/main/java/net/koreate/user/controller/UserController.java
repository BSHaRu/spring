package net.koreate.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.koreate.user.service.UserService;
import net.koreate.user.vo.UserVO;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService us;
	
	@RequestMapping("/signIn")
	public String signIn() {
		return "user/signIn";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "user/signUp";
	}
	
	@PostMapping("/signUpPost")
	public String signUpPost(UserVO vo) throws Exception {
		us.signUp(vo);
		
		return "redirect:/user/signIn";
	}
	
	@PostMapping("/signInPost")
	public String signInPost(
			UserVO vo,
			// HttpSession session
			Model model) throws Exception{
		//session.setAttribute("userInfo", us.signIn(vo));
		model.addAttribute("userInfo",us.signIn(vo));
		
		return "redirect:/";	// home으로 이동
	}
	
	@GetMapping("/signOut")
	public String signOut(
			HttpSession session,
			HttpServletResponse response,
//			HttpServletRequest request,
			@CookieValue(name="signInCookie", required=false) Cookie signInCookie 
			// @CookieValue : HttpServletRequest 할 필요 없이 name값과 동일한 쿠키를 파라미터로 전달 받을 수 있음
			// 해당 쿠키가 없을 수도 있으니 required=false 설정도 같이 해줌
			) {
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			System.out.println("logOut signInCookie : " + signInCookie);
			if(signInCookie != null) {
				signInCookie.setMaxAge(0);
				signInCookie.setPath("/");
				response.addCookie(signInCookie);
			}
		}
		
		return "redirect:/";
	}
	
	
}




