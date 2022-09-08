package net.koreate.mvc.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.koreate.mvc.board.vo.BoardVO;
import net.koreate.mvc.common.util.Criteria;
import net.koreate.mvc.common.util.PageMaker;

public interface BoardService {

	// 게시글 등록
	String register(BoardVO board) throws Exception;
	
	// 게시글 정보 
	BoardVO read(int bno) throws Exception;
	
	// 게시글 수정
	String modify(BoardVO board)throws Exception;
	
	// 게시글 삭제
	String remove(int bno) throws Exception;
	
	// 게시글 조회수 증가
	void updateCnt(HttpServletRequest request, HttpServletResponse response, int bno);
	
	// 검색 게시물 페이징
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	// PageMaker 정보 
	PageMaker getPageMaker(Criteria cri) throws Exception;

}
