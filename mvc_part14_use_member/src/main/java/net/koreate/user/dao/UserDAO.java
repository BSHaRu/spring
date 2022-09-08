package net.koreate.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import net.koreate.user.vo.UserVO;

// 회원 관련 DB 요청 처리
public interface UserDAO {
	
	// 회원가입
	@Insert("INSERT INTO tbl_user(uid, upw, uname) "
			+ " VALUES(#{uid}, #{upw}, #{uname})")
	void signUp(UserVO vo) throws Exception;
	
	// 로그인
	@Select("SELECT * FROM tbl_user " 
			+ " WHERE uid = #{uid} AND upw = #{upw}")
	UserVO signIn(UserVO vo) throws Exception;
	
	// 아이디로 사용자 정보 검색
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid}")
	UserVO getUserById(String uid) throws Exception;
	
	
}
