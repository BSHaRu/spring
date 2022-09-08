package net.koreate.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Controller  : presentation Layer에서 Controller를 명시하기 위해서 사용
 * @Service		: Business Layer Service를 명시
 * @Repository 	: DAO를 명시하기 위해 사용
 * @Component	: 그 외 자동으로 스캔해서 등록하고 싶은 bean을 검색에 포함 (이 친구가 위에 3개를 다 상속하고있음)
 */

@Controller
public class HomeController {
	
	@RequestMapping(value="main.home", method=RequestMethod.GET)
	public String home(HttpSession session) {
		session.setAttribute("a", "home controller");
		// /WEB-INF/views/home.jsp
		return "home";
	}
	
}
