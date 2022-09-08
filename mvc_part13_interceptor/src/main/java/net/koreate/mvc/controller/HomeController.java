package net.koreate.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "home";
	}
	
	@GetMapping("test1")
	public String test() {
		System.out.println("Home Controller test1 요청");
		
		System.out.println("Home Controller test1 처리완료");
		return "home";
	}
	
	@GetMapping("test2")
	public String test2(Model model) {
		System.out.println("Home Controller test2 요청");
		
		model.addAttribute("result", "test2 controller job");
		
		System.out.println("Home Controller test2 처리완료");
		return "home";
	}
	
	@GetMapping("test3")
	public String test3() {
		System.out.println("Home Controller test3 요청");
		System.out.println("Home Controller test3 처리완료");
		return "another";
	}
}







