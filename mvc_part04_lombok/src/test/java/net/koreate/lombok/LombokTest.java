package net.koreate.lombok;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LombokTest {
	
	@Test
	public void lombokTest() {
		System.out.println("Test junit");
		UserVO user1 = new UserVO("id001","pw001","홍길동");
		user1.setUid("id001");
		user1.setUpw("pw001");
		System.out.println(user1);
		
		UserVO uesr2 = new UserVO("id001","pw001","고길동");
		System.out.println(user1.equals(uesr2));
		
		List<String> friendList = new ArrayList<>();
		friendList.add("홍길동");
		friendList.add("고길동");
		UserVO user3 = UserVO.builder()
						.uid("id001")
						.upw("pw001")
						.uno(1)
						.uname("김유신")
//						.friendList(friendList)
						//@Singular("list") 쓰면 아래처럼 list로 나열도 가능
						.list("홍길동").list("고길동").list("심청이") 
						.build();
		// 이런식으로 builder가 있으면 나열해서 값을 채워주고 나서 원하는 타입으로 만들어 줄 수 있음
		System.out.println(user3);
	}
}
