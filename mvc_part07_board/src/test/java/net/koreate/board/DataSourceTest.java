package net.koreate.board;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.board.util.Calculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations= {"classpath:/context/root/root-context.xml"}
)
public class DataSourceTest {

	@Inject
	DataSource ds;
	
	@Test
	public void dataSource() {
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			System.out.println("연결 완료 : " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결 실패 ");
		} finally{
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
	}
	
	@Inject
	SqlSession sqlSession;
	
	@Test
	public void sqlSessionTest() {
		Connection conn = sqlSession.getConnection();
		System.out.println("sqlSession conn : " + conn);
	}
	
//	==============================================================================
	
	@Inject
	Calculator calc;
	
	@Test
	public void calcTest() {
		System.out.println(calc.minus(20, 10));
	}
}
