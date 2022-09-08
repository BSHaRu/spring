package net.koreate.board.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.koreate.board.util.Calculator;

@Configuration // 이친구 없으면  SqlSessionFactory이거 실행할 때 생성되는 sqlSessionFactory()이거랑
// SqlSession sqlSession()여기서 호출될때 실행되는  sqlSessionFactory()이게 달라짐
// -> Configuration가 싱글톤 역할을 해줘서 어노테이션 해줘야 같은값을 가져옴
@MapperScan(basePackages= {"net.koreate.board.dao"})
public class RootConfig {
	
	@Inject
	ApplicationContext context;
	// == spring container
	// BeanFactory == 빈을 관리하고 조회하는 역할
	// 국제화 기능, 환경변수 관련 처리, 애플리케이션 이벤트, 리소스 조회
	
	@Bean	// 메소드가 반환하는 값을 bean이 됨
//	@Scope("prototype")	// scope를 지정안하면 무조건 싱글톤 -> 이렇게 지정하면 ds랑 dmds랑 다른값이 나옴
//	@Scope("singltone")
	public DataSource dataSource() {
		DriverManagerDataSource ds 
			= new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/spring_data");
		ds.setUsername("spring");
		ds.setPassword("12345");
		return ds; 
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		// SqlSessionFactoryBean은 SqlSessionFactory지 bean을 가지고 있지 않음
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource()); // 이게 xml에서 지정한 ref하는거임
		bean.setTypeAliasesPackage("net.koreate.board.vo, net.koreate.board.util");
		// mapper xml
		bean.setMapperLocations(context.getResources("classpath:mybatis/sql/*.xml"));
		
		return bean.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() throws Exception{
		SqlSession session = new SqlSessionTemplate(
				sqlSessionFactory()
		);
		return session;
	}
	
	@Bean
	public Calculator getCalculator() {
		return new Calculator();
	}
	
	
	
}
