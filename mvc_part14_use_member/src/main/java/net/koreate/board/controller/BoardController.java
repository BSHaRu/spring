package net.koreate.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import net.koreate.board.service.BoardService;
import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	// 게시글 목록 페이지
	@GetMapping("listReply")
	public String listReply(
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception{
		List<BoardVO> list = bs.listReply(cri);
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("list",list);
		model.addAttribute("pm",pm);
		
		return "board/listReply";
	}
	
	// 게시글 작성 페이지
	@GetMapping("register")
	public String register() {
		return "board/register";
	}
	
	@PostMapping("register")
	public String register(
				BoardVO vo
			) throws Exception{
		System.out.println("register : " + vo);
		
		bs.register(vo);
		
		return "redirect:/board/listReply";
	}
	
	@GetMapping("readPage")
	public String readPage(
			int bno,
			RedirectAttributes rttr
			) throws Exception{
		// 조회수 증가
		bs.updateCnt(bno);
		rttr.addAttribute("bno", bno);
		return "redirect:/board/read";
	}
	
	@GetMapping("read")
	public String read(int bno, Model model) throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute("board",vo);
		
		return "board/readPage";
	}
	
	// 답변글 작성 페이지 요청
	@GetMapping("replyRegister")
	public String replyRegister(
				int bno,
				Model model) throws Exception{
		BoardVO origin = bs.read(bno);
		model.addAttribute("original", origin);
		return "board/replyRegister";
	}
	
	// 답변글 등록 요청
	@PostMapping("replyRegister")
	public String repleyRegister(BoardVO vo) throws Exception {
		bs.replyRegister(vo);
		return "redirect:/board/listReply";
	}
	
	// 게시글 삭제 요청
	@PostMapping("remove")
	public String remove(int bno) throws Exception{
		bs.remove(bno);
		
		return "redirect:/board/listReply";
	}
	
	// 수정 페이지 요청
	@GetMapping("modifyPage")
	public String modifyPage(int bno, Model model) throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute("board",vo);
		return "board/modifyPage";
	}
	
	// 게시글 수정 완료 요청
	@PostMapping("modifyPage")
	public String modifyPage(
			BoardVO vo,	 // 수정할 게시글 정보
			RedirectAttributes rttr) throws Exception{
		// 게시글 정보 수정
		bs.modify(vo);
		rttr.addAttribute("bno", vo.getBno());
		return "redirect:/board/read";
	}
	
}
