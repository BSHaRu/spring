package net.koreate.di.controller.home;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.koreate.di.dao.TestDAO;
import net.koreate.di.service.TestService;
import net.koreate.di.vo.TestBoardVO;

/** DI(Dependency Injection) Annotation 
-> 객체(bean)를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식
 * 			@Autowired		@Qualifier			@Inject			@Resource 
 * 범용성		스프링 전용			스프링 전용				자바에서 지원		자바에서 지원	
 * 연결성		타입에 맞춰 주입	     특정 객체의 name을 이용		타입에 맞춰 주입		이름으로 주입
 * 							독립적인 사용 x
 */

@Controller
public class HomeController {
	
	@Autowired // : 이 친구(TestService)가 관리하고 있는 동일한 타입의 친구가 있는지 찾아줌
	// -> 실제 구현객체가 뭔지 몰라도 "동일한 타입"을 찾아 주입 시켜줌 
	TestService tsi; // = new TestServiceImpl(); 까지 해줌
	
	@Inject			// 해당 타입을 (TestDAO)을 가진 친구를 찾아줌
	@Named("td")	// TestDAO타입이 여러개면 이름이 td라는 클래스를 찾아줌
	TestDAO dao;
			// 이 프로젝트에서는 servlet-context에서 String을 생성해주고 이름을 지정해줌
	@Resource(name="path") // -> String타입중에 이름이 path를 찾아줌	
	String path;		
	
	@Autowired 	// 동일한 타입이 여러개일경우 이름으로도 찾기는 하지만 Resource보다 속도가 느리다.
	//-> 생성자와 필드가 일치해야 사용 가능
	@Qualifier("profile") // 이름을 다르게 지정 하고 싶을때 Qualifier를 써서 해당 정확한 이름을 가진 친구를 찾아줌
	String profile1;	// ->service.TestServiceImpl 참고바람
	
	@Autowired
	TestBoardVO vo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		if(tsi != null) {
			tsi.testService("Home Controller ");
		}else {
			System.out.println("home tsi is null");
		}
		
		if(dao != null) {
			dao.select("homeController ");
		}else {
			System.out.println("home dao null");
		}
		
		System.out.println("path : " + path);
		System.out.println("profile : " + profile1);
		
		System.out.println(vo);
		return "home";
	}
	
}
