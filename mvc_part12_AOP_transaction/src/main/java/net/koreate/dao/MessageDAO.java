package net.koreate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.koreate.vo.MessageVO;

public interface MessageDAO {

	@Insert("INSERT INTO tbl_message(targetid, sender, message)"
			+ " VALUES(#{targetid}, #{sender}, #{message})")
	void create(MessageVO vo) throws Exception;

	@Select("SELECT * FROM tbl_message ORDER by mno DESC")
	List<MessageVO> list() throws Exception;

	@Select("SELECT * FROM tbl_message WHERE mno = #{mon}")
	MessageVO readMessage(int mno) throws Exception;

	// opendate 수정
	@Update("UPDATE tbl_message SET opendate = now()"
			+ " WHERE mno = #{mno}")
	void updateMessage(int mno) throws Exception;
	
	
}
