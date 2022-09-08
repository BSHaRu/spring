package net.koreate.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.vo.SampleVO;

@Controller
public class SampleController {

	@GetMapping("/testJSON")
	public String toJSON(Model model) {
		model.addAttribute("Hello Json");
		return "JSON";
	}
	
	@GetMapping("getSample")
	@ResponseBody 		// 여기서 찾는건 view가 아니라 data라는걸 표시해주는 친구(view page 전달이 아님!!)
						// -> http요청 할때 body에다가 java Object형태의 data를 "문자열"로 전달 받음
	public SampleVO getSample(SampleVO sample) {	// form값의 파라미터 값을 찾음
		System.out.println("getSample : " + sample);
		// sample : name = 홍길동, age = 15
		// '{name:"홍길동", age:15}' == return 값 == data
		return sample;
	}
	
	// produces : 어떤 타입으로 전달할껀지 알려줌
	@GetMapping(value = "getSampleList", produces="application/json")
	@ResponseBody
	public List<SampleVO> getSampleList(){
		List<SampleVO> sampleList = new ArrayList<>();
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setName("Json"+i);
			vo.setAge(i);
			sampleList.add(vo);
		}
		
		return sampleList;
	}
	
	@PostMapping("getSample")
	@ResponseBody
	public List<SampleVO> listSample(SampleVO vo){
		List<SampleVO> list = new ArrayList<>();
		list.add(vo);
		for(int i=1; i<10; i++) {
			SampleVO add = new SampleVO();
			add.setName(vo.getName()+i);
			add.setAge(vo.getAge()+i);
			list.add(add);
		}
		return list;
	}
	
	@PutMapping("testPUT")
	@ResponseBody
	// request : '{name:"홍길동", age:15}'
	public SampleVO testPUT(@RequestBody SampleVO vo) {	// vo가 일반 파라미터 값이 아니라 json타입이라서
		System.out.println("testPUT : " +vo);			// 앞에 @RequestBody 지정해주는거임
		return vo;							// -> VO객체를 JSON으로 바꿔서 http body에 담아주는 친구
	}				// => 일반적인 form형태(일반 파라미터 - application/x-www-form-urlencoded)로 전달되면 @RequestBody가 필요없고
}					// 'application/json' 즉, json타입으로 전달되면 @RequestBody을  붙여줘야됨
					// * json 타입이 아닌 일반형태일 경우엔 톰캣 버전에 따라서 실행이 안될 수도 있다
					// -> 이땐 Server.server.xml에서 Connector에 parseBodyMethods="PUT,PATCH,DELETE"	을 추가해줘야됨.⁕
