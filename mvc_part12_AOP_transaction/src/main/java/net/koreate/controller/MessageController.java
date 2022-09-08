package net.koreate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.koreate.service.MessageService;
import net.koreate.vo.MessageVO;
import net.koreate.vo.UserVO;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
	
	private final MessageService ms;
	
	@PostMapping("/add")
	public ResponseEntity<String> addMessage(
			MessageVO vo
			){
		ResponseEntity<String> entity = null;
		
		try {
			ms.addMessage(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>("등록 실패", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<MessageVO>> list(){
		ResponseEntity<List<MessageVO>> entity = null;
		
		try {
			List<MessageVO> list = ms.list();
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PatchMapping(value= {
					"/read/{mno}",
					"/read/{mno}/{uid}"})			  // 이건 있을 수도 있고 없을 수도 있는 친구임
	public ResponseEntity<Object> readMessage(		  
			@RequestBody(required = false) UserVO vo, // 있을수도 있고 없을수도 있기 때문에
			@PathVariable("mno") int mno,			  // required = false를 통해서 필수가 아니라고 표시해주는거임
			@PathVariable(name="uid", required = false) String uid	
			){							
		ResponseEntity<Object> entity = null;
		String uvUid = "";
		
		if(uid == null || uid.equals("")) {
			uvUid = vo.getUid();
			uid = vo.getUid();
		}
		
		try {
			MessageVO message = ms.readMessage(uid, mno);
			entity = new ResponseEntity<Object>(message, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}













