package net.koreate.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.koreate.board.dao.BoardDAO;
import net.koreate.board.util.Criteria;
import net.koreate.board.util.PageMaker;
import net.koreate.board.vo.BoardVO;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO dao;

	@Override
	public String regist(BoardVO board) throws Exception {
		int result = dao.create(board);
		String message = (result != 0) ? "Success" : "Failed";
		
		return message;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int totalCount = dao.totalCount();
		pm.setTotalCount(totalCount);
		return pm;
	}

	@Override
	public void updateCnt(int bno, HttpSession session) throws Exception {
		List<Integer> boardReadBno
			=(ArrayList<Integer>)session.getAttribute("boardReadBno");
		if(boardReadBno != null) {
			if(boardReadBno.contains(bno)) {
				System.out.println("이미 read한 게시물임");
				return;
			}
		}else {
			boardReadBno = new ArrayList<>();
		}
		boardReadBno.add(bno);
		session.setAttribute("boardReadBno", boardReadBno);
		dao.updateCnt(bno);
		System.out.println("session 등록 완료");
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

}
