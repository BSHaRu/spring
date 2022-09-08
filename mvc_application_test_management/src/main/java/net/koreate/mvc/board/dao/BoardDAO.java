package net.koreate.mvc.board.dao;

import java.util.List;

import net.koreate.mvc.board.vo.BoardVO;
import net.koreate.mvc.common.util.Criteria;

public interface BoardDAO {
	
	// 게시글 작성
	public int create(BoardVO vo) throws Exception;
	
	// 게시글 상세 보기
	public BoardVO read(int bno) throws Exception;
	
	// 게시글 수정
	public int update(BoardVO vo) throws Exception;
	
	// 게시글 삭제
	public int delete(int bno) throws Exception;
	
	// 조회수 증가
	public void updateCnt(int bno);

	public int listCount() throws Exception;

	public List<BoardVO> listCriteria(Criteria cri);
	
}




