package net.koreate.mvc.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// https://xxxelppa.tistory.com/329 참고

public class TestInterceptor implements HandlerInterceptor{	

	@Override
	// preHandle : 요청 처리가 되기 전. 즉, servlet 요청 처리 전
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler) throws Exception {
		System.out.println("---- 1) preHandle Start");
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		// 디스패처 서블릿의 Controller 호출 정보
		System.out.println("controller : " + method.getBean());
		System.out.println("methodObj : " + methodObj);
		System.out.println("전송 방식 : " + request.getMethod());
		
		String command= request.getRequestURI().substring(
				request.getContextPath().length()+1
			);
		System.out.println("요청 : " + command);
		if(command.equals("test1")) {
			response.sendRedirect(request.getContentType());
			System.out.println("return false");
			return false;	// 서블릿으로 전달 안함 -> 컨트롤러 실행 x
		}
		
		// return super.preHandle(request, response, handler);
		
		System.out.println("---- 1) preHandle END");
		return true;
	}

	@Override
	// postHandle : 요청 처리가 완료 후(~viewResolver 호출하기 전)
	//				-> view가 랜더링 되기 직전에 실행
	public void postHandle(HttpServletRequest request,
						HttpServletResponse response, 
						Object handler,
						ModelAndView modelAndView) throws Exception {
		System.out.println("---- 2) postHandle Start");
		System.out.println(modelAndView.getViewName());
		
		// viewResolver를 호출하기 전에 실행되기 때문에
		// model에 추가 정보를 넣거나 view 를 변경하는 작업이 가능하다
		Map<String, Object> map = modelAndView.getModel();
		for(String key : map.keySet()) {				// test2에만 model값이 있기때문에 key , value값이 나오는거임
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		
		if(modelAndView.getViewName().equals("another")) {
			modelAndView.setViewName("home");
		}
		
		Object result = modelAndView.getModel().get("result");
		if(result == null) {
			modelAndView.addObject("result","postHandle job");
		}
		
		System.out.println("---- 2) postHandle END");
	}

	@Override
	// afterCompletion : 출력이 완료 된 후 (preHandler의 반환값이 true인 경우 실행)
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler, 
								Exception ex) throws Exception {
		System.out.println("---- 3) afterCompletion Start");
		
		System.out.println(request.getAttribute("result"));
		System.out.println(request.getAttribute("result1"));
		
		System.out.println("---- 3) afterCompletion END");
	}
	
	
}
