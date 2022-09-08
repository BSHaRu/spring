package net.koreate.notice.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import net.koreate.common.utils.Criteria;
import net.koreate.common.utils.PageMaker;
import net.koreate.notice.dao.NoticeDAO;
import net.koreate.notice.model.NoticeBoard;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private final NoticeDAO dao;
	
	@Override
	public ArrayList<NoticeBoard> getList(Criteria cri, Model model)  throws Exception{
		int totalCount = dao.count();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		model.addAttribute("pm", pageMaker);
		return dao.getList(cri);
	}

	@Override
	public String write(NoticeBoard board) throws Exception{
		int result = dao.write(board);
		return (result > 0) ? "게시글 작성 성공" : "게시글 작성 실패";
	}

	@Override
	public NoticeBoard read(int notice_num) throws Exception {
		dao.updateViewCnt(notice_num);
		NoticeBoard board = dao.read(notice_num); 
		return board;
	}

	@Override
	public boolean update(NoticeBoard board) throws Exception {
		return dao.update(board) == 1 ? true : false;
	}

	@Override
	public boolean delete(int notice_num) throws IOException, ServletException {
		return (dao.delete(notice_num) != 0) ? true :  false;
	}
	
}
