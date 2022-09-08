package net.koreate.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.koreate.board.util.Criteria;
import net.koreate.board.util.PageMaker;
import net.koreate.board.vo.BoardVO;

public interface BoardService {
	
	// 게시글 작성 - 성공 유무에 따라 메세지 전달
	String regist(BoardVO board) throws Exception;
	
	// 게시글 전체 목록 페이지
	List<BoardVO> listAll() throws Exception;
	
	// 페이징 처리된 리스트 목록
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	// 게시글 수정
	void modify(BoardVO board) throws Exception;
	
	// 게시글 삭제
	void remove(int bno) throws Exception;
	
	// 페이징 정보 처리
	PageMaker getPageMaker(Criteria cri) throws Exception;
	
	// 조회수 증가
	void updateCnt(int bno, HttpSession session) throws Exception;
	
	// 게시글 상세보기
	BoardVO read(int bno) throws Exception;
	
}
