package net.koreate.mvc.sboard.dao;

import java.util.List;

import net.koreate.mvc.common.util.SearchCriteria;
import net.koreate.mvc.sboard.vo.SearchBoardVO;

public interface SearchBoardDAO {

	int create(SearchBoardVO vo) throws Exception;
	
	SearchBoardVO read(int bno) throws Exception;
	
	int update(SearchBoardVO vo) throws Exception;
	
	int remove(int bno) throws Exception;
	
	void updateViewCnt(int bno) throws Exception;
	
	int searchListCount(SearchCriteria cri)throws Exception;
	
	List<SearchBoardVO> searchList(SearchCriteria cri) throws Exception;
	
}
