package net.koreate.board.dao;

import java.util.List;

import net.koreate.board.util.Criteria;
import net.koreate.board.vo.BoardVO;

public interface BoardDAO {
	
	// 게시글 작성
	int create(BoardVO vo) throws Exception;
	
	// 게시글 상세보기
	BoardVO read(int bno) throws Exception;
	
	// 게시글 수정
	void update(BoardVO vo) throws Exception;
	
	// 게시글 삭제
	void delete(int bno) throws Exception;
	
	// 조회수 증가
	void updateCnt(int bno) throws Exception;
	
	// 전체 게시글 목록
	List<BoardVO> listAll() throws Exception;
	
	// 전체 게시물 개수
	int totalCount() throws Exception;
	
	// 페이징 처리된 게시물 목록
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	
}
