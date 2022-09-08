package net.koreate.rest;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import net.koreate.vo.SampleVO;

@RestController
public class ResponseBodyController {

	// 동일한 전송방식이라도 해당 인코딩을 구분 지어주면 오류 안남
	// consumes : 해당 방식으로 소비하겠다
	@PostMapping(value = "xmlTest", consumes="application/x-www-form-urlencoded")
	public ResponseEntity<String> xmlTest(SampleVO vo) throws Exception{
		// HttpEntity
		// Http 프로토콜을 이용하는 통신의 header와 body 관련정보를 저장하는 객체
		
		/*
		 *  http header : (요청/응답)에 대한 요구사항
			http body : header에 대한 내용이 적혀있음
			
			-> Response header 에는 웹서버가 웹브라우저에 응답하는 메시지가 들어있고,
			   Response body에 데이터 값이 들어감
			출처: https://thalals.tistory.com/268 [힘차게, 열심히 공대생:티스토리]
		 */
		
		System.out.println(vo);
		/* xml 형태
			 <member>
			 	<name>홍길동</name>
			 	<age>20</age>
			 </member>
		 */
		String xml = "<sample>";
		xml += "<name>"+vo.getName()+"</name>";
		xml += "<age>"+vo.getAge()+"</age>";
		xml += "</sample>";
		
		HttpHeaders header = new HttpHeaders();
		// Content-Type 넣어주는거임 -> "text/xml","charset=UTF-8" 해줘도 됨
		MediaType type = new MediaType(
				"text",
				"xml",
				Charset.forName("utf-8")
		);
		System.out.println(type);
		System.out.println(MediaType.TEXT_XML);
		System.out.println(MediaType.APPLICATION_JSON_VALUE); // json이면 이렇게 해도 됨
		header.setContentType(type);
		ResponseEntity<String> entity 
			= new ResponseEntity<>(xml, header, HttpStatus.OK); // HttpStatus.OK : 상태코드 200
						// ResponseData(body값), header, Status
		return entity;
	}
	
	@PostMapping(value = "xmlTest", consumes="application/json")
	public String xmlTestJSON(@RequestBody SampleVO vo) throws Exception{
		ObjectMapper mapper = new XmlMapper();
		String xml = mapper.writeValueAsString(vo); // xml형태를 문자열로 바꿔줌
		System.out.println(vo);
		System.out.println(xml);
		
		SampleVO reborn = mapper.readValue(xml, SampleVO.class); // xml형태의 문자열을 js형태로 바꿔줌
		System.out.println("reborn : " + reborn);
		
		mapper = new JsonMapper(); // xml에서 바꿔준것 처럼 JSON도 동일하게 바꿔줄 수 있음
		return xml;
	}
	
}
