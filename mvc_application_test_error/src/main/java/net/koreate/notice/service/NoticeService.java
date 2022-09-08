package net.koreate.notice.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import net.koreate.common.utils.Criteria;
import net.koreate.notice.model.NoticeBoard;

public interface NoticeService{
	
	// 페이징 처리된 게시물 목록
	ArrayList<NoticeBoard> getList(Criteria cri, Model model) throws Exception;
	// 게시글 작성 성공 여부
	String write(NoticeBoard board) throws Exception;
	// 게시글 상세 보기
	NoticeBoard read(int notice_num) throws Exception;
	// 게시글 정보 수정
	boolean update(NoticeBoard board) throws Exception;
	// 게시글 정보 삭제
	boolean delete(int notice_num) throws Exception;
	
}
