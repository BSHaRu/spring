package net.koreate.test.user.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.koreate.test.user.dao.UserDAO;
import net.koreate.test.user.vo.UserVO;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDAO dao;
	
	public int join(UserVO vo) throws Exception {
		return dao.join(vo);
	}

	@Override
	public UserVO login(UserVO vo) throws Exception {
		return dao.login(vo);
	}

}
