package net.koreate.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.koreate.comment.service.CommentService;
import net.koreate.comment.vo.CommentDTO;
import net.koreate.common.utils.Criteria;
import net.koreate.common.utils.PageMaker;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService cs;
	
	@PostMapping("") // post방식으로 comments라고 요청이 들어오면 해당구문 실행
	public ResponseEntity<String> addComment(
				CommentDTO dto){
		System.out.println(dto);
		ResponseEntity<String> entity = null;
		
		try {
			String message = cs.addComment(dto);
			entity = new ResponseEntity<>(message,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/all/{bno}")
	public ResponseEntity<List<CommentDTO>> list(
			@PathVariable(name="bno") int bno // PathVariabl : URL 경로에 변수를 넣어줌
			){						// ->"/all/{bno}"값에   name="bno"의 bno를 넣어줌
		ResponseEntity<List<CommentDTO>> entity = null;
		System.out.println("bno : " + bno);
		
		try {
			List<CommentDTO> list = cs.commentList(bno);
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PatchMapping("/{cno}")
	public ResponseEntity<String> update(
			@PathVariable(name="cno") int cno,
			@RequestBody CommentDTO dto){
		ResponseEntity<String> entity = null;
		System.out.println(cno);
		System.out.println(dto);
		
		dto.setCno(cno);
		try {
			String result = cs.updateComment(dto);
			entity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> delete(
			@PathVariable(name="cno") int cno){
		ResponseEntity<String> entity = null;
		
		try {
			String result = cs.deleteComment(cno);
			entity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 페이징 처리된 리스트 정보
	// "comments/bno/page"
	@GetMapping("/{bno}/{page}")
	public ResponseEntity<Map<String, Object>> listPage(	// bno랑 page 2개 값을 전달받기 위해서 Map을 쓴다는데?
			@PathVariable(name="bno") int bno,
			@PathVariable(name="page") int page
			){
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Map<String, Object> map = new HashMap<>();	// map에 PageMaker랑 게시글 List를 넣어줘야됨?
			Criteria cri = new Criteria(page, 5);
			List<CommentDTO> list = cs.commentListPage(bno, cri);
			map.put("list", list);
			
			//현재 페이지 페이징 블럭 정보
			PageMaker pm = cs.getPageMaker(cri, bno);
			map.put("pm", pm);
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}

















