package net.koreate.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.koreate.board.dao.AttachmentDAO;
import net.koreate.board.dao.BoardDAO;
import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;
import net.koreate.common.utils.SearchPageMaker;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO dao;
	private final AttachmentDAO attachDAO;
	
	@Transactional
	@Override
	public void register(BoardVO vo) throws Exception {
		// INSERT -> title, content, uno 들어감
		dao.regist(vo);
		// origin column 수정
		dao.updateOrigin();
		
		// 업로드된 파일 정보 저장
		List<String> files = vo.getFiles();
		if(files == null) {
			return;
		}
		// db에 저장
		for(String fullName : files) {
			attachDAO.addAttachment(fullName);
		}
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO vo = dao.read(bno);
		
		List<String> fileList = attachDAO.getAttach(bno);
		vo.setFiles(fileList);
		
		return vo;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);
	}

	@Override
	public void replyRegister(BoardVO vo) throws Exception {
		// 기존 글들의 seq 정렬 순서 값이 원본글 보다
		// 큰 값을 가진 글이 존재하면 1씩 증가한 값으로 수정해서 아래쪽으로 이동
		dao.updateReply(vo);
		
		vo.setSeq(vo.getSeq()+1);	// 이전 댓글 번호 증가
		vo.setDepth(vo.getDepth()+1); // 대댓글 번호 증가
		dao.replyRegister(vo);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		
		dao.modify(vo);
		
		// 기존 첨부파일은 테이블에서 삭제 됨
		attachDAO.deleteAttach(vo.getBno());
		
		// 새로  전달된 첨부파일 목록 등록
		List<String> fileList = vo.getFiles();
		if(fileList == null || fileList.isEmpty()) {
			return;
		}
		
		for(String name : fileList) {
			attachDAO.replaceAttachment(vo.getBno(), name);
		}
	}

	@Override
	public void remove(int bno) throws Exception {
		// 첨부 파일 table 삭제
		attachDAO.deleteAttach(bno);
		
		// 게시글 삭제
		dao.remove(bno);
	}

	@Override
	public List<BoardVO> listReply(SearchCriteria scri) throws Exception {
		List<BoardVO> list = dao.listReply(scri);
		return list;
	}

	@Override
	public PageMaker getPageMaker(SearchCriteria scri) throws Exception {
		int totalCount = dao.listCount(scri);
		PageMaker pm = new SearchPageMaker();
		pm.setCri(scri);
		pm.setDisplayPageNum(7);
		pm.setTotalCount(totalCount);
		return pm;
	}

}
