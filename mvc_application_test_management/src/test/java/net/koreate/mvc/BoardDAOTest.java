package net.koreate.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.mvc.board.dao.BoardDAO;
import net.koreate.mvc.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:/context/root-context.xml"}
)
public class BoardDAOTest {
	
	@Autowired
	BoardDAO dao;
	
	@Test
	public void testBoardDAO() throws Exception {
		System.out.println(dao.listCount());
		
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트 제목");
		vo.setContent("테스트 내용");
		vo.setWriter("테스트 글쓴이");
		
		System.out.println(dao.read(1));
		System.out.println(dao.create(vo));
		
	}
	
}
