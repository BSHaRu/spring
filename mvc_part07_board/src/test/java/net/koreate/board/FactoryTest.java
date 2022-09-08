package net.koreate.board;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.board.dao.BoardDAO;
import net.koreate.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations= {"classpath:/context/root/root-context.xml"}
)
public class FactoryTest {
	
	@Inject
	BoardDAO dao;
	
	@Test
	public void factoryBeanTest() {
		
		try {
			System.out.println(dao);
			BoardVO vo = new BoardVO();
			vo.setTitle("세번째");
			vo.setContent("제곧내");
			vo.setWriter("희동이");
			int result = dao.create(vo);
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






