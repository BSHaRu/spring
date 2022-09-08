package net.koreate.user.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.koreate.user.dao.UserDAO;
import net.koreate.user.vo.UserVO;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserDAO dao;

	@Override
	public void signUp(UserVO vo) throws Exception {
		dao.signUp(vo);
	}

	@Override
	public UserVO signIn(UserVO vo) throws Exception {
		return dao.signIn(vo);
	}

	@Override
	public UserVO getUserById(String uid) throws Exception {
		return dao.getUserById(uid);
	}

}
