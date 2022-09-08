package net.koreate.board;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.board.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = {RootConfig.class}
)
public class DataSourceTest {

	@Autowired
	DataSource ds;
	
	@Autowired
	DriverManagerDataSource dmds;
	
	@Autowired
	SqlSessionFactory sf;
	
	@Autowired
	SqlSessionTemplate sst;
	
	@Test
	public void dataSourceTest() throws Exception{
		System.out.println(ds.getConnection());
		System.out.println(ds);
		System.out.println(dmds);
		// ds랑 dmds랑 같은 이유는 싱글톤이라서 그렇다는데?
		// -> 싱글톤 확인하고자 저거 필드 선언한거임
		System.out.println();
		
		System.out.println(sf);
		System.out.println(sst);
		System.out.println("===========================================");
		System.out.println(sf);
		System.out.println(sst.getSqlSessionFactory());
		
	}
	
}
