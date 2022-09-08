package net.koreate.test.user.dao;

import net.koreate.test.user.vo.UserVO;

public interface UserDAO {

	int join(UserVO vo) throws Exception;
	
	UserVO login(UserVO vo) throws Exception;
}
