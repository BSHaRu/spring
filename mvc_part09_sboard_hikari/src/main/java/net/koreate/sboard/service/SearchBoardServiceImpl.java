package net.koreate.sboard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;
import net.koreate.common.utils.SearchPageMaker;
import net.koreate.sboard.dao.SearchBoardDAO;
import net.koreate.sboard.vo.SearchBoardVO;

@Service
@RequiredArgsConstructor
public class SearchBoardServiceImpl implements SearchBoardService {

	private final SearchBoardDAO dao;
	
	@Override
	public String register(SearchBoardVO vo) throws Exception {
		int result = dao.create(replace(vo));
		String msg = "글쓰기 성공";
		if(result == 0) {
			msg = "글쓰기 실패";
		}
		return msg;
	}

	@Override
	public String modify(SearchBoardVO vo) throws Exception {
		return dao.update(replace(vo)) != 1 ? "수정 실패" : "수정 성공";
	}

	@Override
	public String remove(int bno) throws Exception {
		return dao.remove(bno) > 0 ? "삭제 성공" : "삭제 실패" ;
	}

	@Override
	public SearchBoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void updateViewCnt(HttpServletRequest request, 
							HttpServletResponse response,
							int bno) throws Exception {
		String cookieBno = "sboardCookie" + bno;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieBno)) {
					// 쿠키값이 존재하면 이대로 return 해서 종료
					return; 
				}
			}
		}
		// 쿠키가 존재하지 않으면 이하 실행
		Cookie cookie = new Cookie(cookieBno, bno+""); // bno+"" == String.valueOf(bno)
		String path = request.getContextPath()+"/sboard/";
		System.out.println(path);
		cookie.setPath(path);
		cookie.setMaxAge(60*60); // 1시간
		response.addCookie(cookie);
		dao.updateViewCnt(bno);
	}

	@Override
	public List<SearchBoardVO> list(SearchCriteria cri) throws Exception {
		String type = cri.getSearchType();
		String word = cri.getKeyword();
		System.out.println("type : " + type);
		System.out.println("keyword : " + word);
		
		List<SearchBoardVO> list = dao.serchList(cri);
		if(type != null 
				&& !type.trim().equals("") 
				&& !type.equals("n")) {
			for(SearchBoardVO vo : list) {
				// 제목 | 작성자
				vo.setTitle(highLight(vo.getTitle(), word));
				vo.setWriter(highLight(vo.getWriter(), word));
			}
		}
		System.out.println(list);		
		return list;
	}
	// 검색 한 결과값에 뭘 검색했는지 텍스트에 하이라이트 적용
	public String highLight(String text, String word) {	// 수정할 텍스트 | 검색한 키워드
		text = text.replace(word, "<b style='color:red;'>"+word+"</b>");
		return text;
	}
	
	// < > 특수 문자 치환 -> 장난질 못하게 막으려는거임
	public String replaceScript(String text) {
		// < == &lt;
		// > == &gt';
		text = text.replace("<", "&lt");
		text = text.replace(">", "&gt");
		
		return text;
	}
	
	// 글쓰기 혹은 수정될 객체 정보에 특수문자 치환
	public SearchBoardVO replace(SearchBoardVO vo) {
		vo.setTitle(replaceScript(vo.getTitle()));
		vo.setContent(replaceScript(vo.getContent()));
		vo.setWriter(replaceScript(vo.getWriter()));
		
		return vo;
	}

	@Override 
	public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
		int totalCount = dao.searchListCount(cri);
		// PageMaker의 cri는 Criteria타입임
		// 근데 그냥 Criteria는 검색기능이 없고, SearchCriteria는 검색기능이 있어
		// 그래서 PageMaker를 가져오는게 아니라 SearchPageMaker타입으로 가져오는거임
		// -> pm은 PageMaker타입이지만, 자식인 SearchPageMaker를 가지고 있음
		// PageMaker는 SearchPageMaker가져올수도 있고, 그냥 PageMaker를 가져올 수도 있음
		// => 이걸 우리는 다양성이라고 부름
		PageMaker pm = new SearchPageMaker();
		pm.setCri(cri);
		pm.setTotalCount(totalCount);
		return pm;
	}

	@Override
	public Map<String, Object> getListModel(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
