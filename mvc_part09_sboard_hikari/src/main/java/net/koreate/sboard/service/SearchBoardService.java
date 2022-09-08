package net.koreate.sboard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;
import net.koreate.sboard.vo.SearchBoardVO;

public interface SearchBoardService {

	// 게시글 등록
	String register(SearchBoardVO vo) throws Exception;
	
	// 게시글 수정
	String modify(SearchBoardVO vo) throws Exception;
	
	// 게시글 삭제
	String remove(int bno) throws Exception;
	
	// 게시글 상세보기
	SearchBoardVO read(int bno) throws Exception;
	
	// 게시글 조회수 증가 - cookie를 이용
	void updateViewCnt(
			HttpServletRequest request,
			HttpServletResponse response,
			int bno
			) throws Exception;
	
	// 검색 게시글 페이징 처리된 목록
	List<SearchBoardVO> list(SearchCriteria cri) throws Exception;
	
	// 페이징 블럭 정보
	PageMaker getPageMaker(SearchCriteria cri) throws Exception;
	
	// 통합
	Map<String, Object> getListModel(SearchCriteria cri) throws Exception;
}
