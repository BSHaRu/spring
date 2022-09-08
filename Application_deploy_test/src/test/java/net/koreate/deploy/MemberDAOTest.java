package net.koreate.deploy;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.deploy.member.dao.MemberDAO;
import net.koreate.deploy.member.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
		"classpath:/spring/root-context.xml",
		"classpath:/spring/appServlet/servlet-context.xml"
	}
)
public class MemberDAOTest {

	@Inject 
	MemberDAO dao;
	
	@Test
	public void testDAORead() {
		MemberVO vo = new MemberVO();
		vo.setId("admin");
		vo.setPass("admin");
		MemberVO read = dao.read(vo);
		System.out.println(read);
		
	}
	
	
}
