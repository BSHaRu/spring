package net.koreate.mvc.sboard.service;

import java.util.List;

import net.koreate.mvc.common.util.PageMaker;
import net.koreate.mvc.common.util.SearchCriteria;
import net.koreate.mvc.sboard.vo.SearchBoardVO;

public interface SearchBoardService {
	
	// 게시글 등록
	String register(SearchBoardVO vo) throws Exception;
	
	// 게시글 수정
	String modify(SearchBoardVO vo) throws Exception;
	
	// 게시글 삭제
	String remove(int bno) throws Exception;
	
	// 게시글 정보 
	SearchBoardVO read(int bno) throws Exception;
	
	// 게시글 조회수 증가
	void updateViewCnt(int bno) throws Exception;
	
	// 검색 게시물 페이징
	List<SearchBoardVO> searchList(SearchCriteria cri) throws Exception;
	
	// PageMaker 정보 
	PageMaker getPageMaker(SearchCriteria cri)throws Exception;
	
}









