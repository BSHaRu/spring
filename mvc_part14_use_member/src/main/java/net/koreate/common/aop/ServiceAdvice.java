package net.koreate.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ServiceAdvice {
	
	@Around("execution(* net.koreate.*.service.*.*(..))")
	public Object checkService(
				ProceedingJoinPoint pjp
			) throws Throwable{
		//전처리
		log.info("----- Around ServiceAdvice Start ");
		
		log.info("----- target : " + pjp.getTarget());
		log.info("----- method : " + pjp.getSignature().getName());
		log.info("----- params : " + Arrays.toString(pjp.getArgs()));
		
		Object o = pjp.proceed(); // 실체 메소드 실행
		// 후처리
		log.info("----- return values : " + o);
		
		log.info("----- Around ServiceAdvice End ");
		return o;
	}
}
