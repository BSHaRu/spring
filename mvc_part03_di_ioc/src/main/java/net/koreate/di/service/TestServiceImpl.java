package net.koreate.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.koreate.di.dao.TestDAO;

@Service	// 객체 생성이 가능한 곳에 어노테이션 해줘야됨 -> 인터페이스는 객체 생성을 못하기때문에 해주면 안됨
public class TestServiceImpl implements TestService {

	private TestDAO dao;
	
	@Autowired	// set메소드는 반드시 명시해줘야 주입해줌
				// -> TestDAO는 2개가 있지만 dao로 지정한건 없기때문에 오류를 발생한다.
	@Qualifier("td") // => 그런데 dao를 바꾸기 귀찮으니깐 @Qualifier 추가해서 이름을 지정해주면 해결된다. 
	public void setDao(TestDAO dao) {
		this.dao = dao;
	}

	@Override
	public void testService(String message) {
		System.out.println(message + " : test service");
		System.out.println("dao : " + dao);
	}
	
}
