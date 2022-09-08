package net.koreate.board.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import net.koreate.board.dao.BoardDAO;

public class Calculator {

	@Inject
	BoardDAO dao;
	
	public Calculator() {
		System.out.println("Calculator 생성");
		System.out.println(dao + " : 의존성 주입 전");
	}
	
	public int minus(int a, int b) {
		return a - b;
	}
	
	@PostConstruct 		// 의존성 주입이 완료되고 bean 사용 준비가 완료되면 호출
	public void init() {
		System.out.println("객체 생성 후 생성");
		System.out.println(dao + " : 의존성 주입 완료");
	}
	
	@PreDestroy		// 사라지기 직전에 호출 
	public void destroy() {
		System.out.println("Calculator destroy");
	}
}
