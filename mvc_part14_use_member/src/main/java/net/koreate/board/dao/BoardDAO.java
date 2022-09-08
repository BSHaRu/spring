package net.koreate.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import net.koreate.board.provider.BoardQueryProvider;
import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.SearchCriteria;

public interface BoardDAO {

	// 게시글 등록
//	@Insert("INSERT INTO re_tbl_board(title, content, uno) "
//			+ " VALUES(#{title}, #{content}, #{uno})")
	@InsertProvider(type=BoardQueryProvider.class, method="register")
	void regist(BoardVO vo) throws Exception;
	
	// 가장 최근에 등록된 프라이머리 키 값으로 origin column 값 수정
	@Update("UPDATE re_tbl_board SET origin = LAST_INSERT_ID() "
			+ " WHERE bno = LAST_INSERT_ID()")
	void updateOrigin() throws Exception;
	
	// 검색 결과 내에서 페이징 처리된 게시글 목록
	/*
	 * @Select("SELECT R.*, U.uname AS writer FROM " +
	 * " re_tbl_board AS R NATURAL JOIN tbl_user AS U " +
	 * " ORDER BY R.origin DESC, R.seq " + " ASC limit #{startRow}, #{perPageNum}")
	 */ 
	@SelectProvider(type=BoardQueryProvider.class, method="searchSelectSql")
	List<BoardVO> listReply(SearchCriteria cri) throws Exception;
	
	// 검색 결과 내의 전체 게시글 개수
//	@Select("SELECT count(*) FROM re_tbl_board")
	@SelectProvider(type=BoardQueryProvider.class, method="searchSelectCount")
	int listCount(SearchCriteria cri) throws Exception;
	
	// bno가 일치하는 게시글 정보
	@Select("SELECT R.*, U.uname AS writer FROM "
			+ " re_tbl_board AS R NATURAL JOIN tbl_user AS U "
			+ " WHERE R.bno = #{bno}")
	BoardVO read(int bno) throws Exception;
	
	// 조회수 증가
	@Update("UPDATE re_tbl_board SET " 
			+ " viewcnt = viewcnt + 1 " 
			+ " WHERE bno = #{bno}")
	void updateCnt(int bno) throws Exception;
	
	// 답변글 등록
//	@Insert("INSERT INTO re_tbl_board(title, content, origin, depth, seq, uno)"
//			+ " VALUES(#{title}, #{content}, #{origin}, #{depth}, #{seq}, #{uno})")
	@InsertProvider(type=BoardQueryProvider.class, method="register")
	void replyRegister(BoardVO vo) throws Exception;
	
	// 답변글 등록 시 정렬 값 수정
	@Update("UPDATE re_tbl_board SET " 
			+ " seq = seq + 1"
			+ " WHERE origin = #{origin} "
			+ " AND seq > #{seq}")
	void updateReply(BoardVO vo) throws Exception;
	
	// 게시글 수정
	@Update("UPDATE re_tbl_board SET "
			+ " title = #{title}, "
			+ " content = #{content}, "
			+ " updatedate = now() " 
			+ " WHERE bno = #{bno}")
	void modify(BoardVO vo) throws Exception;
	
	// 게시글 삭제
	// showboard = 'n'으로 수정해줘야됨
	@UpdateProvider(type=BoardQueryProvider.class, method="remove")
	void remove(int bno) throws Exception;
	
}
