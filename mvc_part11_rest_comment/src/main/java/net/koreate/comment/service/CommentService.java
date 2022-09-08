package net.koreate.comment.service;

import java.util.List;

import net.koreate.comment.vo.CommentDTO;
import net.koreate.common.utils.Criteria;
import net.koreate.common.utils.PageMaker;

public interface CommentService {
	// 게시글 번호를 전달 받아서  게시글 번호와 일치하는 전체 댓글 리스트
	List<CommentDTO> commentList(int bno) throws Exception;
	
	// 댓글 삽입
	String addComment(CommentDTO dto) throws Exception;
	
	// 댓글 수정
	String updateComment(CommentDTO dto) throws Exception;
	
	// 댓글 삭제
	String deleteComment(int cno) throws Exception;
	
	// 페이징 처리된 댓글 리스트
	List<CommentDTO> commentListPage(int bno, Criteria cri) throws Exception;
	
	// 페이징 블럭 정보
	PageMaker getPageMaker(Criteria cri, int bno) throws Exception;
	
}
