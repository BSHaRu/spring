package net.koreate.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import net.koreate.user.vo.LoginDTO;
import net.koreate.user.vo.UserVO;

public interface UserDAO {
	
	@Insert("INSERT INTO tbl_user(uid,upw,uname) VALUES(#{uid},#{upw},#{uname})")
	void signUp(UserVO vo) throws Exception;
	
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid} AND upw= #{upw}")
	UserVO signIn(LoginDTO dto)throws Exception;
	
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid}")
	UserVO getUserById(String uid)throws Exception;
	
	@Insert("INSERT INTO ban_ip(ip) VALUES(#{ip})")
	void signInFail(String ip) throws Exception;
	
}
