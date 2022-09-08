package net.koreate.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.koreate.board.provider.BoardQueryProvider;

public interface AttachmentDAO {

	// 첨부 파일 등록
	@Insert("INSERT INTO tbl_attach(fullName, bno)"
			+ " VALUES(#{fullName}, LAST_INSERT_ID())")
	void addAttachment(String fullName) throws Exception;

	// 게시글 번호와 일치하는 첨부파일 목록
	@Select("SELECT fullName FROM tbl_attach WHERE bno = #{bno}")
	List<String> getAttach(int bno) throws Exception;

	// 게시글 첨부파일 table 목록에서 삭제
	// @Delete("DELETE FROM tbl_attach WHERE bno = #{bno}")
	@DeleteProvider(type=BoardQueryProvider.class, method="deleteAttach")
	void deleteAttach(int bno) throws Exception;

	@Insert("INSERT INTO tbl_attach(bno, fullName) "
			+ " VALUES(#{bno}, #{fullName})")
	void replaceAttachment(
			@Param("bno") int bno, 
			@Param("fullName") String name) throws Exception;
	
	// 이전 날짜에 등록된 첨부 파일 가져옴
	@Select("SELECT fullName FROM tbl_attach "
			+ " WHERE DATE_FORMAT(regdate,'%Y-%m-%d') "
			+ " = "
			+ " DATE_SUB(CURDATE(), interval 1 day)")
	List<String> getTrashAttach() throws Exception;
	
	
}
