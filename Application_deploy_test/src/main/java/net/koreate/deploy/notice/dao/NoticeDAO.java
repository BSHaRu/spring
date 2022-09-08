package net.koreate.deploy.notice.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.koreate.deploy.notice.model.NoticeBoard;
import net.koreate.deploy.util.Criteria;

public interface NoticeDAO{
	
	@Select("SELECT * FROM notice_board ORDER BY notice_date DESC limit #{pageStart}, #{perPageNum}")
	public ArrayList<NoticeBoard> getList(Criteria cri);

	@Select("SELECT count(*) FROM notice_board")
	public int count();

	@Select("SELECT * FROM notice_board WHERE notice_num = #{notice_num}")
	public NoticeBoard read(int notice_num);

	@Insert("INSERT INTO notice_board "
	+ "VALUES(null,#{notice_category},#{notice_author},#{notice_title},#{notice_content},now())")
	public int write(NoticeBoard noticeBoard);

	@Update("UPDATE notice_board SET "
			+ " notice_category = #{notice_category}, "
			+ " notice_author = #{notice_author}, "
			+ " notice_title = #{notice_title}, "
			+ " notice_content = #{notice_content}, "
			+ " notice_date = now() "
			+ " WHERE notice_num = #{notice_num}")
	public int update(NoticeBoard board);

	@Delete("DELETE FROM notice_board WHERE notice_num = #{notice_num}")
	public int delete(int notice_num);
	
	
}
