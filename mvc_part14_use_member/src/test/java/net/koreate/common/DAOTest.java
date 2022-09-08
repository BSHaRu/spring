package net.koreate.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.user.dao.UserDAO;
import net.koreate.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:context/**/*-context.xml"}	// ** : 해당하위폴더가 뭐가 되었던 상관없이 
)
public class DAOTest {
	
	@Autowired
	UserDAO dao;
	
	@Test
	public void userDAOtest() throws Exception{
		UserVO vo = dao.getUserById("id001");
		System.out.println(vo);
	}
	
	
}
