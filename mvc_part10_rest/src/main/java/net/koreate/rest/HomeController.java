package net.koreate.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import net.koreate.vo.SampleVO;

@Controller
public class HomeController {
	

	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	// GET | POST | PUT | PATCH | DELETE
	@GetMapping("methodTest")
	public void methodTest() {
	}
	
	@GetMapping("resData")
	public void resData(){
	}
	
	@GetMapping("javascript")
	public void javascript() {
	}
	
	@GetMapping("ajaxTest")
	public void ajaxTest() {
	}
	
	@GetMapping("testXml")
	public String testXml() {
		return "xmlTest";
	}
	
	@PostMapping("methodTest")
	public void methodTest(SampleVO vo) {
		System.out.println("POST");
		System.out.println(vo);
	}
	
//	@RequestMapping(value="methodTest", method=RequestMethod.PUT)
	@PutMapping("methodTest")
	public String methodTestPUT(SampleVO vo, Model model) {
		System.out.println("put");
		System.out.println(vo);
		
		model.addAttribute(vo);
		return "JSON";
	}
	
	
	
	
	
	
	
}
