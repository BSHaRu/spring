package net.koreate.deploy.member.dao;

import org.apache.ibatis.annotations.Select;

import net.koreate.deploy.member.model.MemberVO;

public interface MemberDAO {
	
	@Select("SELECT * FROM mvc_member WHERE id = #{id} AND pass = #{pass}")
	MemberVO read(MemberVO member);

}
