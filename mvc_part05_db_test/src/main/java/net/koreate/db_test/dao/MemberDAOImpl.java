package net.koreate.db_test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.db_test.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace="net.koreate.mapper.MemberMapper";
	
	@Override
	public void insertMember(MemberVO memberVO) {
		// net.koreate.mapper.MemberMapper.insertMember
		session.insert(namespace+".insertMember", memberVO);
	}

	@Override
	public MemberVO readMember(String userid) {
		MemberVO member = session.selectOne(namespace+".readMember",userid);
		return member;
	}

	@Override
	public MemberVO readMemberWithPass(String userid, String pass) {
		Map<String, String> map = new HashMap<>();
		map.put("id", userid);
		map.put("pw", pass);
		
		MemberVO vo = session.selectOne(
			namespace+".readWithPass"
			,map
		);
		return vo;
	}

	@Override
	public List<MemberVO> readMemberList() {
		return session.selectList(namespace+".memberList");
	}

}
