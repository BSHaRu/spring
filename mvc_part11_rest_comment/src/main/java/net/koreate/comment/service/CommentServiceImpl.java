package net.koreate.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.koreate.comment.dao.CommentDAO;
import net.koreate.comment.vo.CommentDTO;
import net.koreate.common.utils.Criteria;
import net.koreate.common.utils.PageMaker;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentDAO dao;
	
	@Override
	public List<CommentDTO> commentList(int bno) throws Exception {
		return dao.commentList(bno);
	}

	@Override
	public String addComment(CommentDTO dto) throws Exception {
		int result = dao.add(dto);
		return result == 1 ? "댓글 성공" : "댓글 실패";
	}

	@Override
	public String updateComment(CommentDTO dto) throws Exception {
		int result = dao.update(dto);
		return result > 0 ? "수정 성공" : "수정 실패";
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		return dao.delete(cno) > 0 ? "삭제 성공" : "삭제 실패";
	}

	@Override
	public List<CommentDTO> commentListPage(int bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri, int bno) throws Exception {
		int totalCount = dao.totalCount(bno); // 필수로 받아야 되는 정보임
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setDisplayPageNum(5);
		pageMaker.setTotalCount(totalCount);
		
		return pageMaker;
	}

}
