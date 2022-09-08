package net.koreate.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.koreate.dao.MessageDAO;
import net.koreate.vo.MessageVO;

/* spring AOP 특징
	- 프록시 패턴 기반의 AOP 구현체, 프록시 객체를 쓰는 이유는 접근 제어 및 부가기능을 추가하기 위해서임
 	- 스프링 빈에만 AOP를 적용 가능
 	- 모든 AOP 기능을 제공하는 것이 아닌 스프링 IoC와 연동하여 엔터프라이즈 애플리케이션에서 
 	가장 흔한 문제(중복코드, 프록시 클래스 작성의 번거로움, 객체들 간 관계 복잡도 증가 ...)에 대한 
 	해결책을 지원하는 것이 목적
출처: https://engkimbs.tistory.com/746 [새로비:티스토리]
 */

/* AOP 개념
	- Aspect : 흩어진 관심사를 모듈화 한 것. 주로 부가기능을 모듈화함.
		-> PointCut(어디에 적용할 것인지) / Advice(해야할 일, 기능)이 필요
	- Target : Aspect를 적용하는 곳 (class, method ... 등)
	- Advice : 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가기능을 담은 구현체
	- JointPoint : Advice가 적용될 위치, 끼어들 수 있는 지점, 메서드 진입 지점, 
				생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
	- PointCut : JointPoint의 상세한 스펙을 정의한 것. 
				'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음
출처: https://engkimbs.tistory.com/746 [새로비:티스토리]
 */

/* 각 어노테이션이 하는 역할
	@Before (이전) : 어드바이스 타겟 메소드가 호출되기 전에 어드바이스 기능을 수행
	@After (이후) : 타겟 메소드의 결과에 관계없이(즉 성공, 예외 관계없이) 
				타겟 메소드가 완료 되면 어드바이스 기능을 수행
	@AfterReturning (정상적 반환 이후) :타겟 메소드가 성공적으로 결과값을 반환 후에 어드바이스 기능을 수행
	@AfterThrowing (예외 발생 이후) : 타겟 메소드가 수행 중 예외를 던지게 되면 어드바이스 기능을 수행
	@Around (메소드 실행 전후) : 어드바이스가 타겟 메소드를 감싸서 타겟 메소드 호출전과 후에 어드바이스 기능을 수행
출처: https://engkimbs.tistory.com/746 [새로비:티스토리]
 */

@Component	// bean으로 등록하기 위해서 씀
@Aspect		
@Slf4j		// homeController에 정의된 Logger랑 똑같은 친구임 -> 로그 찍는 친구
public class AOPAdvice {

	public AOPAdvice() {
		System.out.println("AOPAdvice 생성");
		log.info("logger AOP advice");
	}
	
	// execution(접근제한자 | 리턴타입  | 패키지명.클래스이름.메소드 이름(매개변수))
	// 접근제한자 : public | private 등 -> 기본값 public / 생략가능
	// .. : 개수 상관없이 모든 매개변수를 의미
	@Before("execution(* net.koreate.service.*.*(..))")	// 리턴타입 : * / service의 모든 class / 모든 메소드(매개변수 상관x)
	public void startLog(JoinPoint jp) throws RuntimeException{
		log.info("------------------------------------------------------");
		log.info("------------------Start Log START----------------------");
		// net.koreate.service.MessageService
		log.info("target : " + jp.getTarget()); 
		log.info("type : " + jp.getKind());	// 생성자인지 메소드인지 확인
		// 실행 된 메소드 이름
		log.info("name : " + jp.getSignature().getName());
		
		Object[] objs = jp.getArgs();	// 파라미터값을 배열로 가져옴 -> 모든 메소드를 들고오기 위해서 Object로 받음
		log.info("parameters : " + Arrays.toString(objs));
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		log.info("Start Log time : " + sdf.format(new Date()));
		log.info("-------------------------------------------------------");
		log.info("--------------------Start Log END----------------------");
	}
	
	@After("execution(* net.koreate.service.MessageService.*(..))")
	public void endLog(JoinPoint jp) throws RuntimeException{
		log.info("--------------------------------------------------------");
		log.info("-------------------End Log START------------------------");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		log.info("End Log time : " + sdf.format(new Date()));
		log.info("--------------------------------------------------------");
		log.info("-------------------End Log END--------------------------");
	}
									
	@AfterReturning(	// AfterReturning 이친구는 리턴값을 확인하는 친구라서 !void를 해서 무조건 리턴값을 가진 친구만 찾겠다는거임
						// 그렇다고 void를 못찾는건 아닌데, 그럴꺼면 After를 쓰지 AfterReturning 쓸 필욘 없지 않을까?
			pointcut = "execution(!void net.koreate.service.MessageService.*(..))",
			returning = "returnValue"	// returnValue : 해당 메서드의 리턴객체를 그대로 가져올 수 있다
			)							// -> 리턴값이 다양하니깐 Object를 씀
	public void returningTest(JoinPoint jp, Object returnValue) throws RuntimeException{
		log.info("---------------- AfterReturning Start --------------------");
		log.info("AfterReturning target : " + jp.getTarget()); 
		log.info("AfterReturning type : " + jp.getKind());
		log.info("AfterReturning name : " + jp.getSignature().getName());
		log.info("AfterReturning returns : " + returnValue);
		
		Object[] objs = jp.getArgs();	
		log.info("AfterReturning parameters : " + Arrays.toString(objs));
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		log.info("AfterReturning start time : " + sdf.format(new Date()));
		log.info("---------------- AfterReturning End -----------------------");
	}
	
	@Around("execution(* net.koreate.controller.MessageController.*(..))")
	// dao를 직접 구현하면 dao가 가능하지만, 현재 프로젝트에서는 mybatis로 인터페이스에 적용시킨거라서 dao는 적용이 안됨
	public Object controllerLog(ProceedingJoinPoint pjp) throws Throwable { //ProceedingJoinPoint 진행가능한 join point
		log.info("----------------Around Controller Start--------------------");
		long startTime = System.currentTimeMillis();
		
		log.info("Around target : " + pjp.getTarget());
		log.info("Around name : " + pjp.getSignature().getName());
		log.info("Around parameters : " + Arrays.toString(pjp.getArgs()));
		
		Object o = pjp.proceed();		// 수행 결과에 따라서 전처리, 후처리(리턴값 전달받고)를 알 수 있음
		
		if(o != null) {
			log.info("Around Object : " + o);
			log.info("Around Object : " + o.toString());
		}
		
		long endTime = System.currentTimeMillis();
		log.info("work time : " + (endTime - startTime));
		log.info("----------------Around Controller End--------------------");
		log.info("                                                        ");
		
		return o;	// 여기서 return 값을 안주면 디스패처 서블릿은 아무정보 값도 넘겨받지 못함
	}			// -> void타입(리턴x -> null)일 수도 있으니 Object타입으로  return을 해주는거임

	@Autowired
	private MessageDAO dao;
	
	// MessageService에서 readMessage 메소드에 부가기능 추가 해줄꺼임
	@Around(value="execution(net.koreate.vo.MessageVO net.koreate.service.MessageService.readMessage(..)) && args(uid,mno)")
	public Object readMessageCheck(ProceedingJoinPoint pjp, String uid, int mno) throws Throwable {
		log.info("---- readMessage Around Start ----");
		
		Object[] args = pjp.getArgs();	// 이친구는 값을 확인 하는건 좋지만, 값을 쓰기엔 복잡하다(해당 index를 찾아서 써야되니깐)
		log.info("args : " + args);		// -> && args(uid,mno) 추가해주고, 
										// 해당 값을 (String uid, int mno)여기에 넣어서 사용하기 위해 지정
		
		MessageVO messageVO = dao.readMessage(mno); // 해당 dao 정보가져오기 위해서 Autowired 해줌
		log.info(messageVO.toString());			// log는 문자열만 가능, 객체는 전달x ->.toString 해주는거임
		if(messageVO.getOpendate() != null) {
			log.info("---- throw readMessage Around End ----");
			throw new NullPointerException("이미 읽은 메세지");
		}
		
		Object o = pjp.proceed();
		
		if(o != null && o instanceof MessageVO) {
			messageVO = (MessageVO)o;
			log.info("readMessage Around : " + messageVO);
		}
		log.info("---- readMessage Around END ----");
		return o;		
	}
	
	@AfterThrowing(
			value = "execution(* net.koreate.service.*.*(..))",
			throwing = "exception")
	public void endThrowingLog(JoinPoint jp, Exception exception) {
		log.info("		----- After Throwing Start -----");
		log.info("target : " + jp.getTarget());
		log.info("name : " + jp.getSignature().getName());
		
		log.warn("error : " + exception.getMessage());	// 어떤 오류 발생했는지 체크
		
		log.info("		----- After Throwing End -----");
	}
	
}












