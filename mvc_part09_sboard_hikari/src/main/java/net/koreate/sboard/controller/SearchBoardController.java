package net.koreate.sboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;
import net.koreate.sboard.service.SearchBoardService;
import net.koreate.sboard.vo.SearchBoardVO;

@Controller
@RequestMapping("/sboard/*")
@RequiredArgsConstructor
public class SearchBoardController {
	
	private final SearchBoardService sbs;
		
	// 게시판 리스트를 보여줌
	@GetMapping("listPage") // 클라이언트가 요청한 url -> jsp파일을 보여주는게 아님
	public ModelAndView listPage(ModelAndView mav,	// 여기서 디스패처서블릿이 해당 클래스를 기본생성자로 생성하기때문에
						SearchCriteria cri, 		// SearchCriteria에 기본생성자를 추가해줘야된다.
						HttpServletRequest request) throws Exception{ // + 해당 클래스의 필드값을 다 확인하고 
															// 그 필드값들을 set해서 해당 매개변수(cri)에 저장까지 해줌
		mav.setViewName("sboard/listPage"); // 이게 클라이언트에게 실제로 보여주는 jsp파일 페이지
							// -> Mapping할 때 이름과 ViewName의 이름은 달라도 되는데 ViewName이름은 실제로 존재하는 jsp파일 이름이어야됨.
		System.out.println("listPage : " + cri);
		List<SearchBoardVO> list = sbs.list(cri);
		mav.addObject("list",list);	// Model은 request.setAttribute한거랑 똑같다고 보면됨
									// -> "list"는 request에 저장되어있음
		
		/* mav.addObject("list",list);를 재민이가 조금 풀어서 써놓은거
		ModelMap mm = mav.getModelMap();
		
		for(String key : mm.keySet()) {
			request.setAttribute(key, mm.get(key));
		}
		
		List<SearchBoardVO> findList = (List<SearchBoardVO>)mm.get("list");
		request.setAttribute("list", findList);
		request.getAttribute("list");
		// jsp에서 값을 받으려면 영역객체에 파라미터가 있어야되는데
		// 모델에 넣으면 지알아서 인식이 되는가?
		// -> 디스패처서블릿이 모델을 잠깐 컨트롤러에 지급했다가  다시 가져간다.
		// 다시가져가서 영역객체인 request에 담겨있는 데이터를 넣는다
		// request에 넣으면 jsp는 알 수 있으니깐
		// 왜? forward로 넘기면 request 정보를 그대로 가지고 있으니깐!
		// 참고로 forward로 넘기면 url도 안바뀐 상태에서 페이지가 이동되기 됨 		*/
		
		// 페이징 정보
		PageMaker pm = sbs.getPageMaker(cri);
		mav.addObject("pm",pm);
		
		return mav;
	}
	
	@GetMapping("register")
	public String register() throws Exception{
		return "sboard/register";
	}
	
	// 글을 새로 작성하는 거니깐 첫 페이지로 이동 시킬꺼임
	@PostMapping("register")
	public String register(SearchBoardVO vo, RedirectAttributes rttr) throws Exception{
		String msg = sbs.register(vo);
		
		rttr.addFlashAttribute("result",msg);
		return "redirect:/sboard/listPage";
	}
	
	// 게시글 상세보기
	@GetMapping("readPage")
	public String readPage(
				HttpServletRequest request,
				HttpServletResponse response,
				int bno,
				// 넘겨 받을때 makeQuery를 같이 넘겨받기때문에 해당값 지정해주는거임
				@ModelAttribute("cri") SearchCriteria cri	// 해당이름이 너무 길기때문에 어노테이션 활용해서 cri라고 쓸꺼임
				) throws Exception{
		sbs.updateViewCnt(request, response, bno);
		
		SearchBoardVO vo = sbs.read(bno);
//		model.addAttribute("board",vo);	// request 받았기때문에 model이 필요없어짐
		request.setAttribute("board",vo);
		
		return "sboard/readPage";
	}
	
	// 수정 페이지 요청
	@GetMapping("modifyPage")
	public ModelAndView modifyPage(
				int bno,
				@ModelAttribute("cri") SearchCriteria cri,	// 이게 없으면 수정 요청할 때 어떤 페이지 정보를 가지고 있는지를 모름
				ModelAndView mav) throws Exception {
		mav.addObject("board",sbs.read(bno));	// 게시물 정보 저장 
		mav.setViewName("sboard/modifyPage");	// 보여줄 페이지 저장
		
		return mav;
	}
	
	// 게시글 수정 요청 처리
	@PostMapping("modifyPage")
	public String modifyPage(
				SearchBoardVO vo,
				SearchCriteria cri,
				RedirectAttributes rttr
			) throws Exception{
		System.out.println(vo);
		System.out.println(cri);
		
		String msg = sbs.modify(vo);
		rttr.addFlashAttribute("result",msg);
//		rttr.addFlashAttribute("cri",cri); // 이렇게 전달 할 경우 새로고침 할 때 해당값이 사라져 버림
		// 고로 이렇게 노가다 해줘야됨
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword()); 
		
		rttr.addAttribute("bno",vo.getBno()); // readPage로 이동할때 bno가 필요하다고 지정했기때문에
											// bno가 없이(null상태) 전달되면 오류발생함
		return "redirect:/sboard/readPage";	// -> 해당값(bno)를 같이 전달하겠다고 하는거
	}
	
	// 게시글 삭제 요청 처리
	@PostMapping("removePage")
	public String removePage(
			int bno,
			SearchCriteria cri,
			RedirectAttributes rttr
			) throws Exception{
		String msg = sbs.remove(bno);
		rttr.addFlashAttribute("result", msg);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword()); 
		
		return "redirect:/sboard/listPage";
	}
	
	
	
	
}
