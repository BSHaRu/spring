package net.koreate.notice.dao;

import java.util.ArrayList;

import net.koreate.common.utils.Criteria;
import net.koreate.notice.model.NoticeBoard;

public interface NoticeDAO{
	
	// 페이징 처리된 게시글 목록
	ArrayList<NoticeBoard> getList(Criteria cri);

	// 전체 게시물 개수
	int count();

	// 상세보기 페이지 - 한개의 게시물 정보
	NoticeBoard read(int notice_num);
	
	// 조회 수 증가 
	int updateViewCnt(int notice_num);

	// 게시물 작성
	int write(NoticeBoard board);
	
	// 게시물 수정
	int update(NoticeBoard t);

	// 게시물 삭제
	int delete(int notice_num);
}
