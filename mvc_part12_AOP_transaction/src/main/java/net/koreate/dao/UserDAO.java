package net.koreate.dao;

import org.apache.ibatis.annotations.Update;

import net.koreate.vo.UserVO;

public interface UserDAO {

	@Update("UPDATE tbl_user SET "
			+ " upoint = upoint + #{upoint}"
			+ " WHERE uid = #{uid}")
	void updatePoint(UserVO uv) throws Exception ; 

	
}
