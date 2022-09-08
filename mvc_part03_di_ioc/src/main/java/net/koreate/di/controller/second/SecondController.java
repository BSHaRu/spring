package net.koreate.di.controller.second;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.koreate.di.dao.TestDAO;
import net.koreate.di.service.TestService;

@Controller
public class SecondController {
	
	@Autowired(required=false) // second-context에는 context-scan에 service를 안해놨기때문에 오류발생
	TestService ts;		// -> required=false는 해당값을 못찾으면 null로 표시 => 잘 안쓰임
	
	@Inject
	@Named("testDAOImpl")
	TestDAO dao;
	
	// /WEB-INF/view/main.jsp
	@RequestMapping("main")
	public void doMain() {
		System.out.println("second doMain 호출");
		if(dao != null) {
			dao.select("second controller");
		}else {
			System.out.println("dao null");
		}
		
		if(ts != null) {
			ts.testService("second controller ");
		}else {
			System.out.println("second controller ts is null");
		}
		
	}
}
