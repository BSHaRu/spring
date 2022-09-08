package net.koreate.db_test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.db_test.dao.MemberDAO;
import net.koreate.db_test.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
)
public class DataSourceTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void testDataSource() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println(conn + " : 연결 완료!!");
			System.out.println("=========================================================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testSqlSessionFactory() {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println("연결 정보 객체 생성 완료");
			System.out.println(session);
			System.out.println(session.getConnection());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	============================================================================
	
	@Autowired
	MemberDAO dao;
	
//	@Test
	public void testInsertMember() {
		MemberVO vo = new MemberVO();
		vo.setUserid("id006");
		vo.setUserpw("pw006");
		vo.setUsername("홍두깨");
		dao.insertMember(vo);
		System.out.println(vo);
	}
	
	@Test
	public void testReadMember() {
		MemberVO m = dao.readMember("id001");
		System.out.println("readMember : " + m);
		
		MemberVO member = dao.readMemberWithPass("id001", "pw001");
		System.out.println("readWithPass : " + member);
		
		System.out.println("====================================================================================");
		List<MemberVO> memberList = dao.readMemberList();
		for(MemberVO vo : memberList) {
			System.out.println(vo);
		}
	}
	
}
