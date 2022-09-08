package net.koreate.board.util;

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
	
	public void init() {
		System.out.println("객체 생성 후 생성");
		System.out.println(dao + " : 의존성 주입 완료");
	}
	
	public void destroy() {
		System.out.println("Calculator destroy");
	}
}
