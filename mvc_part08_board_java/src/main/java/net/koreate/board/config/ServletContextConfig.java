package net.koreate.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	// 설정을 하겠다고 알려주는 친구 + 싱글톤 보장해줌
@EnableWebMvc	// annotation-driven 역할 해주는 친구
@ComponentScan(basePackages= {"net.koreate.board.controller", "net.koreate.board.service"})
public class ServletContextConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	
	
	/*
//	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"> 대체해주는 친구
	@Bean
	public ViewResolver customViewResolver() {
		InternalResourceViewResolver bean
			= new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}
	*/
}
