package net.koreate.mvc;

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
	locations = {
		"classpath:/context/root-context.xml",
		"classpath:/context/root-servlet.xml"
	}
)
@WebAppConfiguration
public class MVCTest {

	@Autowired
	private WebApplicationContext wc;
	
	private MockMvc mvc;
	
	@Before 
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	
	
	//@Test
	public void testLsitPage() throws Exception{
		ModelAndView mav = mvc.perform(
			MockMvcRequestBuilders.get("/sboard/listPage").param("page", "1")
			.param("perPageNum", "5").param("searchType", "t").param("keyword", "배고파")
		).andReturn().getModelAndView();
		
		ModelMap map = mav.getModelMap();
		
		Map<String, Object> obj = mav.getModel();
		for(String key : obj.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + obj.get(key));
		}
	}
	
	
	//@Test
	public void testRegister() throws Exception{
		ResultActions ra = mvc.perform(
				MockMvcRequestBuilders.post("/sboard/register").param("title","배고파")
				.param("content", "테스트 내용").param("writer", "테스트 작성자")
				);
		MvcResult result = ra.andReturn();
		ModelAndView mav = result.getModelAndView();
		FlashMap flash = result.getFlashMap();
		System.out.println("=========================================");
		System.out.println(mav);
		System.out.println("flash : " + flash.entrySet()); 
		System.out.println("=========================================");
	}
	
	
	@Test
	public void testReadDetail() throws Exception{
		ModelMap map = mvc.perform(
			MockMvcRequestBuilders.get("/sboard/readDetail").param("bno", "1")
		).andReturn().getModelAndView().getModelMap();
		
		System.out.println("redDetail : " + map);
	}
	
	
	
}
