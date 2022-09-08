package net.koreate.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import net.koreate.board.service.BoardService;
import net.koreate.board.util.Criteria;
import net.koreate.board.util.PageMaker;
import net.koreate.board.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor 	// 필수 인자값을 생성자로 만들어줌 
// -> 이거 없으면 final로 지정한값에 빨간줄 생김
public class BoardController {
	
	private final BoardService bs;
	
	// @RequestMapping(value="/register", method=RequestMethod.GET)
	@GetMapping("/register") // get방식 호출일 경우 register로 이동
	public void register()throws Exception{
		// /board/register
		System.out.println("게시글 작성 페이지 요청");
	}
	
	@PostMapping("/register")
	public String register(
			BoardVO board,
//			Model model,
//			HttpSession session
			RedirectAttributes rttr
			) throws Exception{
		System.out.println(board);
		String result = bs.regist(board);
		System.out.println(result);
		
//		model.addAttribute("msg",result);
//		session.setAttribute("message", result);
		
		// addFlashAttribute : 데이터 전달 | 영역객체를 저장되어서 el을 활용 가능함
		rttr.addFlashAttribute("result",result);
		
		return "redirect:/board/listAll"; // 리스트 페이지 이동
	}
	
	@GetMapping("/listAll")
	public void listAll(Model model) throws Exception{
		List<BoardVO> list = bs.listAll();
		model.addAttribute("list",list);
	}
	
	@GetMapping("/read")
	public void read(
			@RequestParam(name="bno") int bno,
			Model model,
			HttpSession session
			) throws Exception{
		// 조회수 증가
		bs.updateCnt(bno, session);
		// 게시글 전달
		BoardVO boardVO = bs.read(bno);
		model.addAttribute("boardVO",boardVO);
	}
	
	@GetMapping("/modify")
	public void modify(
			int bno,	// param 생략 가능
			Model model
			) throws Exception{
		model.addAttribute("boardVO",bs.read(bno));
	}
	
	@PostMapping("/modify")
	public String modify(
			BoardVO board,
			RedirectAttributes rttr
			) throws Exception{
		// 게시글 수정
		bs.modify(board);
		
		// addAttribute : 파라미터 전달 | rttr을 쓰면 아래처럼 return에 주소값을 저래 길게 안써도됨
		rttr.addAttribute("bno",board.getBno());
		return "redirect:/board/read";
													// 여러개를 전달할 경우 +&이하 작성해야됨
//		return "redirect:/board/read?bno="+board.getBno()+"&title="+board.getTitle();
	}
	
	@GetMapping("/remove")
	public String remove(
				int bno,	// 삭제할 게시글 번호
				RedirectAttributes rttr
			)throws Exception{
		bs.remove(bno);
		
		rttr.addFlashAttribute("result","삭제 완료");
		return "redirect:/board/listAll";
	}
	
	// 페이징 처리
	@GetMapping("/listPage")
	// param : 요청 페이지 | perPageNum
	public void listPage(Criteria cri, Model model) throws Exception{
		List<BoardVO> list = bs.listCriteria(cri);
		PageMaker pm = bs.getPageMaker(cri);
		
		model.addAttribute("list",list);
		model.addAttribute("pm",pm);
	}
	
	@GetMapping("readPage")
	public String readPage(
			int bno,
			Model model,
			@ModelAttribute("cri") Criteria cri // model에다가 해당 클래스를 등록시키겠다. ()안에 있는 이름으로 사용하겠다.
			) throws Exception{
		System.out.println(cri);
		System.out.println(bno);
//		model.addAttribute("cri",cri); // 이거 안해도 model에 cri정보가 같이 넘어간다는데?
		model.addAttribute("boardVO",bs.read(bno));
		return "/board/readPage";
	}
	
	@GetMapping("/modifyPage")
	public void modifyPage(
				int bno,
				@ModelAttribute("cri") Criteria cri,
				Model model
			) throws Exception{
		model.addAttribute("boardVO",bs.read(bno));
	}
	
	@PostMapping("/modifyPage")
	public String modifyPage(
			BoardVO board,
			Criteria cri,
			RedirectAttributes rttr
			) throws Exception{
		bs.modify(board);
		/*
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		*/
		rttr.addFlashAttribute("cri",cri);
		rttr.addAttribute("bno",board.getBno());
		return "redirect:/board/readPage";
	}
	
	@PostMapping("/removePage")
	public String remove(
				int bno,
				Criteria cri,
				RedirectAttributes rttr
			) throws Exception{
		bs.remove(bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		// 여기에서는 해당값을 param으로 넘겨줘야됨 
		// -> addFlash 쓰면 안됨.. why????
		return "redirect:/board/listPage";
	}
}
