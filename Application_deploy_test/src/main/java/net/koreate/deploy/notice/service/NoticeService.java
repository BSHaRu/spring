package net.koreate.deploy.notice.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.koreate.deploy.notice.dao.NoticeDAO;
import net.koreate.deploy.notice.model.NoticeBoard;
import net.koreate.deploy.util.Criteria;
import net.koreate.deploy.util.PageMaker;

@Service
@RequiredArgsConstructor
public class NoticeService{
	
	private final NoticeDAO dao;
	
	
	public Map<String,Object> getList(Criteria cri) {
		Map<String,Object> map = new HashMap<>();
		map.put("noticeList",dao.getList(cri));
		int totalCount = dao.count();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		map.put("pm", pageMaker);
		return map;
	}

	
	public void write(NoticeBoard NoticeBoard){
		dao.write(NoticeBoard);
	}

	public NoticeBoard read(int notice_num){
		return dao.read(notice_num);
	}

	public void update(NoticeBoard board){
		dao.update(board);
	}

	public void delete(int notice_num){
		dao.delete(notice_num);
	}
	
}
