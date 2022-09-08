package net.koreate.sboard.dao;

import java.util.List;

import net.koreate.common.utils.SearchCriteria;
import net.koreate.sboard.vo.SearchBoardVO;

public interface SearchBoardDAO {
	
	// 게시물 정보 삽입
	int create(SearchBoardVO vo) throws Exception;
	
	// 게시글 정보 수정
	int update(SearchBoardVO vo) throws Exception;
	
	// 게시글 정보 삭제
	int remove(int bno) throws Exception;
	
	// 게시글 상세보기 - bno와 일치하는 게시글 정보
	SearchBoardVO read(int bno) throws Exception;
	
	// 조회수 증가
	void updateViewCnt(int bno) throws Exception;
	
	// 검색된 게시물 목록
	List<SearchBoardVO> serchList(SearchCriteria cri) throws Exception;
	
	// 검색된 게시물의 전체 개수
	int searchListCount(SearchCriteria cri) throws Exception;
	
}
