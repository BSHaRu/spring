package net.koreate.test.user.controller;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.koreate.test.user.service.UserService;
import net.koreate.test.user.vo.UserVO;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService us;
	
	@GetMapping("login")
	public void login() throws Exception{}
	
	@GetMapping("join")
	public void join() throws Exception{}
	
	
	/**
	 * @see Request Login 
	 * @method POST
	 * @url /user/login
	 * @param userid userpw
	 * @return redirect:/ 
	 */
	@PostMapping("login")
	public String loginPost(
			UserVO vo, 
			Model model
			) throws Exception{
		model.addAttribute("user",us.login(vo));
		
		return "redirect:/";
	}
	
	
	
	/**
	 * @see Request Join
	 * @method POST
	 * @url /user/join
	 * @param userid / userpw / username / email
	 * @return redirect:/user/login
	 */
	@PostMapping("join")
	public String joinPost(UserVO vo) throws Exception{
		us.join(vo);
		
		return "redirect:/user/login";
	}
	

}
