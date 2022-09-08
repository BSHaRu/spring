package net.koreate.mvc.sboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.mvc.common.util.PageMaker;
import net.koreate.mvc.common.util.SearchCriteria;
import net.koreate.mvc.sboard.dao.SearchBoardDAO;
import net.koreate.mvc.sboard.vo.SearchBoardVO;

@Service
public class SearchBoardServiceImpl implements SearchBoardService{
	
	@Inject
	SearchBoardDAO dao;
	
	@Override
	public String register(SearchBoardVO vo) throws Exception {
		int result = dao.create(vo);
		return getResult(result);
	}
	
	@Override
	public String modify(SearchBoardVO vo) throws Exception {
		int result = dao.update(vo);
		return getResult(result);
	}

	@Override
	public String remove(int bno) throws Exception {
		int result = dao.remove(bno);
		return getResult(result);
	}

	@Override
	public SearchBoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void updateViewCnt(int bno) throws Exception {
		dao.updateViewCnt(bno);
	}

	@Override
	public List<SearchBoardVO> searchList(SearchCriteria cri) throws Exception {
		return dao.searchList(cri);
	}

	@Override
	public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
		int totalCount = dao.searchListCount(cri);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(totalCount);
		return pm;
	}
	
	public String getResult(int result) {
		return (result > 0 ) ? "SUCCESS" : "FAIL";
	}
	
}
