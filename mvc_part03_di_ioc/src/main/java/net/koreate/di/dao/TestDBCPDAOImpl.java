package net.koreate.di.dao;

import org.springframework.stereotype.Repository;

@Repository("td") // 이름을 지정안해주면 클래스명의 첫글자가 소문자로 됨 -> testDBCPDAOImpl.뭐시깽이 이런식
public class TestDBCPDAOImpl implements TestDAO {

	@Override
	public void select(String message) {
		System.out.println(message + " DBCP Select");
	}

}
