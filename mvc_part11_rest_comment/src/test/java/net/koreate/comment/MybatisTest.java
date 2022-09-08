package net.koreate.comment;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.comment.dao.CommentDAO;
import net.koreate.comment.vo.CommentDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations= {"classpath:/spring/root-context.xml"}
)
public class MybatisTest {

	@Inject
	SqlSessionFactory sqlSessionFactiory;
	
	@Test // DB 연결 제대로 되었는지 테스트
	public void test1SqlSessionFactiory() {
		SqlSession session = sqlSessionFactiory.openSession();
		System.out.println(session.getConnection());
	}
	
	@Autowired
	CommentDAO dao;
	
	@Test // 댓글 달리는지 테스트
	public void test2Comment() throws Exception {
		CommentDTO dto = new CommentDTO();
		dto.setBno(1);
		dto.setCommentAuth("홍길동");
		dto.setCommentText("1등");
		int result = dao.add(dto);
		System.out.println("댓글번호 : " + result);
	}
	
	
	
	
	
	
}
