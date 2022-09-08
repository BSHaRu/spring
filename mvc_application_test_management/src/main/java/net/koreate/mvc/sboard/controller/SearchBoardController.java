package net.koreate.mvc.sboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.mvc.common.util.SearchCriteria;
import net.koreate.mvc.sboard.service.SearchBoardService;
import net.koreate.mvc.sboard.vo.SearchBoardVO;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

	@Inject
	SearchBoardService service;
	
	@GetMapping("register")
	public void register() throws Exception{}
	
	@PostMapping("register")
	public String register(
				SearchBoardVO vo,
				RedirectAttributes rttr
			)throws Exception{
		rttr.addFlashAttribute("result",service.register(vo));
		return "redirect:/sboard/listPage";
	}
	
	@GetMapping("readPage")
	public String readPage(
			SearchCriteria cri,
			@RequestParam("bno") int bno,
			RedirectAttributes rttr)throws Exception{

		service.updateViewCnt(bno);

		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("bno",bno);
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/sboard/readDetail";
	}
	
	@GetMapping("readDetail")
	public String readDetail(@ModelAttribute("cri") SearchCriteria cri, int bno, Model model) throws Exception{
		model.addAttribute("board",service.read(bno));
		return "sboard/readPage";
	}
	
	@GetMapping("modifyPage")
	public String modifyPage(int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model)throws Exception{
		model.addAttribute("board",service.read(bno));
		return "/sboard/modifyPage";
	}
	
	@PostMapping("modifyPage")
	public String modifyPage(SearchBoardVO board, SearchCriteria cri, RedirectAttributes rttr)throws Exception {
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		String msg = service.modify(board);
		rttr.addFlashAttribute("result",msg);
		
		return "redirect:/sboard/listPage";
	}
	
	@PostMapping("removePage")
	public String removePage(
			int bno,
			SearchCriteria cri,
			RedirectAttributes rttr
			) throws Exception{
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		rttr.addFlashAttribute("result",service.remove(bno));
		return "redirect:/sboard/listPage";
	}
	
	@GetMapping("listPage")
	public String listPage(
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception{
		
		model.addAttribute("list",service.searchList(cri));
		model.addAttribute("pageMaker",service.getPageMaker(cri));
		
		return "sboard/listPage";
	}
	
	
}
