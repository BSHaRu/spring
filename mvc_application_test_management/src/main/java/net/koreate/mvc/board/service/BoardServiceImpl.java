package net.koreate.mvc.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import net.koreate.mvc.board.dao.BoardDAO;
import net.koreate.mvc.board.vo.BoardVO;
import net.koreate.mvc.common.util.Criteria;
import net.koreate.mvc.common.util.PageMaker;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDAO dao;

	@Override
	public String register(BoardVO board) throws Exception {
		return getMessage(dao.create(board));
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public String modify(BoardVO board) throws Exception {
		return getMessage(dao.update(board));
	}

	@Override
	public String remove(int bno) throws Exception {
		return getMessage(dao.delete(bno));
	}

	@Override
	public void updateCnt(
			HttpServletRequest request, 
			HttpServletResponse response, 
			int bno) {
		
		String cookieBno = "boardCookie"+bno;
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie != null && cookie.getName().equals(cookieBno)) {
				return;
			}
		}
		
		Cookie cookie = new Cookie(cookieBno,bno+"");
		cookie.setPath("/");
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
		dao.updateCnt(bno);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dao.listCount());
		return pageMaker;
	}
	
	public String getMessage(int result) {
		return (result != 0) ? "SUCCESS" : "FAILED";
	}
	
	
}







