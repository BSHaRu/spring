package net.koreate.controller.doCon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DoController {
	
	@RequestMapping("main.do")
	public void main() {
		System.out.println("main.do 호출");
	}
}
