package net.koreate.user.service;

import net.koreate.user.vo.UserVO;

public interface UserService {
	
	// 회원 가입
	void signUp(UserVO vo) throws Exception;
	
	// 로그인
	UserVO signIn(UserVO vo) throws Exception;
	
	// 아이디로 사용자 정보 확인
	UserVO getUserById(String uid) throws Exception;
	
}
