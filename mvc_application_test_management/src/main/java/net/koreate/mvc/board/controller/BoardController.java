package net.koreate.mvc.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.mvc.board.service.BoardService;
import net.koreate.mvc.board.vo.BoardVO;
import net.koreate.mvc.common.util.Criteria;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@GetMapping("/reg")
	public String registerGET() throws Exception{
		return "board/reg";
	}
	
	@PostMapping("/reg")
	public String registerPost(BoardVO board, RedirectAttributes rttr) 
	throws Exception{
		System.out.println("register Post 요청");
		System.out.println(board);
		String result = service.register(board);
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String listPage(
			@ModelAttribute("cri") Criteria cri,
			Model model,
			ModelAndView mav
			)throws Exception{
		
		model.addAttribute("list",service.listCriteria(cri));
		model.addAttribute("pageMaker",service.getPageMaker(cri));
		return "board/list";
	}
	
	@GetMapping("/read")
	public String readPage(
			@RequestParam(name="bno") int bno,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("cri") Criteria cri) throws Exception{
		System.out.println(cri);
		service.updateCnt(request, response, bno);
		model.addAttribute("boardVO",service.read(bno));
		return "board/read";
	}
	
	@GetMapping("/modify")
	public String modifyGet(
			int bno, 
			@ModelAttribute("cri") Criteria cri,
			Model model) throws Exception{
		model.addAttribute("boardVO", service.read(bno));
		return "board/modify";
	}
		
	@PostMapping("/modify")
	public String modifyPost(
			RedirectAttributes rttr,
			Criteria cri,
			BoardVO board) throws Exception{
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("result",service.modify(board));
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(
			int bno,
			Criteria cri,
			RedirectAttributes rttr
			) throws Exception{
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("result",service.remove(bno));		
		
		return "redirect:/board/list";
	}
	
}
