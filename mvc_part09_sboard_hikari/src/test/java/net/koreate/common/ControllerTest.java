package net.koreate.common;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"/root-context.xml","/servlet-context.xml"}	
)
// 이거 하는 이유 : view 파일이 없어도 우리가 만든 기능이 제대로 돌아가는지 확인하려고 test 하는거래
// => 프로젝트가 크면 클수록 test를 더 많이 하니깐 매우매우 중요한거래
@WebAppConfiguration // controller 및  web 환경에 사용되는 bean을 자동으로 생성하고 등록해줌
// -> WebApplicationContext를 생성 할 수 있도록 해주는 친구임
public class ControllerTest {
	
	
	@Autowired
	private WebApplicationContext wc; // 맵핑이랑 컴포넌트 정보가 다 들어있데
	// -> 이친구를 쓸라면 @WebAppConfiguration친구가 있어야 된다는데?
	// 
	
	private MockMvc mvc;	// 가상의 DispatcherServlet
	// -> 이친구를 사용하기 위해 WebApplicationContext 이친구가 필요하다는데?
	
	@Before // before를 해줌으로써 test전에 미리 MockMvc를 객체를 만들어 둠
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	
//	@Test 
	public void testRead() throws Exception{
		// perform : 수행하다.
		/**
		 MockMvcRequestBuilders를 사용해 설정 한 요청 데이터를 수행
		  ->perform
		 */
		
		ModelMap map = mvc.perform(
			MockMvcRequestBuilders.get("/sboard/listPage")
		).andReturn().getModelAndView().getModelMap();
		System.out.println("testRead : " + map);
	}
	
//	@Test
	public void readBoard() throws Exception{
		System.out.println("readBoard() =================================================");
		ModelAndView mav = mvc.perform(
				MockMvcRequestBuilders.get("/sboard/readPage").param("bno", "300")
				).andReturn().getModelAndView();
		ModelMap map = mav.getModelMap();
		
		Map<String, Object> obj = mav.getModel();
		for(String key : obj.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + obj.get(key));
		}
		System.out.println("viewName : " + mav.getViewName());
	}
	
//	@Test
	public void insertBoardTest() throws Exception{
		ResultActions ra = mvc.perform(
				MockMvcRequestBuilders.post("/sboard/register").param("title", "테스트 제목")
				.param("content", "테스트 내용").param("writer", "작성자")
				);
		MvcResult result = ra.andReturn();
		ModelAndView mav = result.getModelAndView();
		FlashMap flash = result.getFlashMap();
		System.out.println("=========================================");
		System.out.println(mav);
		System.out.println("flash : " + flash.entrySet()); // entrySet : key와 value 한쌍을 묶은 친구
		System.out.println("=========================================");
	}
	
	@Test
	public void modifyBoardTest() throws Exception{
		ResultActions ra = mvc.perform(
				MockMvcRequestBuilders.post("/sboard/modifyPage").param("bno","300")
				.param("title", "수정 제목").param("content", "수정 내용").param("writer", "수정 작성자")
				);
		MvcResult result = ra.andReturn();
		ModelAndView mav = result.getModelAndView();
		FlashMap flash = result.getFlashMap();
		System.out.println("=========================================");
		System.out.println(mav);
		System.out.println("flash : " + flash.entrySet()); 
		System.out.println("=========================================");
	}
}
