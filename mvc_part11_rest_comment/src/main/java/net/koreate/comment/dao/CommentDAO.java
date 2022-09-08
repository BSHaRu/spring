package net.koreate.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.koreate.comment.vo.CommentDTO;
import net.koreate.common.utils.Criteria;

public interface CommentDAO {
	
	@Select("SELECT * FROM tbl_comment "
			+ " WHERE bno = #{bno}"
			+ " ORDER BY cno DESC")
	List<CommentDTO> commentList(int bno) throws Exception;
	
	@Insert("INSERT INTO tbl_comment(bno, commentText, commentAuth) "
			+ " VALUES(#{bno}, #{commentText}, #{commentAuth})")
	int add(CommentDTO dto) throws Exception;
	
	@Update("UPDATE tbl_comment SET "
			+ " commentAuth = #{commentAuth}, "
			+ " commentText = #{commentText}, "
			+ " updatedate = now() "
			+ " WHERE cno = #{cno} ")
	int update(CommentDTO dto) throws Exception;
	
	@Delete("DELETE FROM tbl_comment "
			+ " WHERE cno = #{cno}")
	int delete(int cno) throws Exception;
	
	// mybatis는 사용할 수 있는 매개변수가 1개밖에 전달이 안됨
	// -> 그래서 Map으로 묶어서 전달해야되는데 이미 서비스에서 매개변수 2개를 받는다고 지정해놨다
	// => 이걸 해결 해주는게 @Param을 지정해주면 알아서? Map형태로 바꿔가꼬 해당 key값으로? 넣어줌
	@Select("SELECT * FROM tbl_comment "
			+ " WHERE bno = #{bno} "
			+ " ORDER by cno DESC "
			+ " limit #{cri.startRow}, #{cri.perPageNum}")
	List<CommentDTO> listPage(
			@Param("bno") int bno, 
			@Param("cri") Criteria cri) throws Exception;
	
	
	@Select("SELECT count(*) FROM tbl_comment "
			+ " WHERE bno = #{bno}")
	int totalCount(int bno) throws Exception;
	
	
}
