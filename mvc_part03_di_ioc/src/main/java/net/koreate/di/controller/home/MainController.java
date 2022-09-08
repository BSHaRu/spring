package net.koreate.di.controller.home;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import net.koreate.di.dao.TestDAO;
import net.koreate.di.service.TestService;

@Controller
@RequiredArgsConstructor	// final은 한번 생성될때 초기화 해줘야 되니
public class MainController { //  이걸 쓰면 어짜피 필수 생성자를 만들어 주기 때문에 코드가 간단해짐
	
	private final TestService ts;
	private final TestDAO td;
	
	// 생성자를 통해서 어노테이션 쓰는걸 권장함
	// -> 스프링에서는 bean만 생성 되어있으면 알아서 주입 해주지만,
	// 다른 환경에서는 그걸 인식 못하기때문에 
	// 최소한 생성자를 만들어 놓으면 해당 어노테이션을 인식 못하는 환경이라도
	// 사용자가 따로 추가해서 테스트를 사용 할 수 있으니깐 생성자를 통해서 사용하는게 좋다.
//	@Autowired // 생성자가 1개면 생략은 가능하다 
//	public MainController(TestService ts, TestDAO td) {
//		this.ts = ts;
//		this.td = td;
//	}
	
	@PostConstruct
	public void init() {
		System.out.println("main postConstuct");
		ts.testService("Main ");
		td.select("main ");
	}
	
	
}
