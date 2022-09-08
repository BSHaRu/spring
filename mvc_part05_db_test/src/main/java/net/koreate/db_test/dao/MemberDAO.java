package net.koreate.db_test.dao;

import java.util.List;

import net.koreate.db_test.vo.MemberVO;

public interface MemberDAO {
	
	void insertMember(MemberVO memberVO);
	
	MemberVO readMember(String userid);
	
	MemberVO readMemberWithPass(String userid, String pass);
	
	List<MemberVO> readMemberList();
}
