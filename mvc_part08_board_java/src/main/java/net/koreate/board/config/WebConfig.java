package net.koreate.board.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 최초에 was에 의해서 생성 호출 되는 class를 구현한 객체
// web.xml이 있으면 web.xml을 보완해주고 없으면 web.xml을 대체하는 친구
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletContextConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter filter
			= new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true); // true로 되어있으면 무조건 utf-8로 지정됨 | 기본값은 false임
		// -> 다른곳에서 인코딩이 다른걸로 지정 되어있더라도 utf-8로 지정되서 넘어오게끔 해줌
		return new Filter[] {filter}; // 여러개 등록가능하니깐 배열로 받음
	}
	
	
	
}
