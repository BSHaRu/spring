package net.koreate.common.session;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import net.koreate.user.vo.UserVO;

// HttpSession = > session 정보를 저장하는 객체
// HttpSessionEvent
// => HttpSession 객체가 생성 또는 제거 될때 발생하는 이벤트
// HttpSessionEventListener 
// => event 처리를 하는 class

// HttpSessionAttributeListener 
// session 속성값이 추가 수정 삭제 되었을때 발생하는
// HttpSessionBindingEvent 처리를 하는 객체
@Component
public class MySessionEventListener
		implements HttpSessionListener, HttpSessionAttributeListener{

	public static Hashtable<String, HttpSession> sessionRepository;
	
	public MySessionEventListener() {
		if(sessionRepository == null) {
			sessionRepository = new Hashtable<>();
		}
	}
	
	public boolean expireDuplicatedSession(String uid, String sessionId) {
		System.out.println("Active Session Count : "+sessionRepository.size());
		//Set<String> keySet = sessionRepository.keySet();
		for(HttpSession s : sessionRepository.values()) {
			UserVO vo = (UserVO)s.getAttribute("userInfo");
			// 사용자 정보가 존재하고
			// 해당 사용자 정보와 파라미터로 전달된 사용자 아이디가 같을 경우
			if(vo != null && vo.getUid().equals(uid)) {
				// 중복 로그인 요청
				if(!s.getId().equals(sessionId)) {
					// session 등록된 session 아이디 값과
					// 현재 로그인 요청이 들어온  session 아이디값이 틀리면
					// 서로 다른 브라우저에서 로그인 요청이 들어온거임.
					System.out.printf("중복 로그인 user %s, sessionId %s %n ",vo.getUid(),sessionId);
					// s.removeAttribute("userInfo");
					s.setAttribute("invalidate", "중복 로그인으로 로그아웃 됩니다. 싸우세요.");
					return true;
				}
			}
		}
		return false;
	}
	
	
	// session 객체에 속성값이 추가되었을 때
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded 호출");
		System.out.printf("SESSION ID : %s %n",event.getSession().getId());
		System.out.printf("SESSION 추가된 attribute name : %s : value : %s %n",event.getName(),event.getValue());
		if(event.getName().equals("userInfo")) {
			HttpSession session = event.getSession();
			sessionRepository.put(session.getId(), session);
		}
	}
	
	// session removeAttribute
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.printf("SESSION 삭제된 attribute name : %s : value : %s %n",event.getName(),event.getValue());
		HttpSession session = event.getSession();
		if(event.getName().equals("userInfo")) {
			sessionRepository.remove(session.getId());
		}
	}
	
	// session.setAttribute 기존 키값이 이미 존재하는 값  
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.printf("SESSION 수정된 attribute name : %s : value : %s %n",event.getName(),event.getValue());
	}
	
	// Session 객체가 생성이 되었을때
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.printf("생성된 SESSION ID %s %n",se.getSession().getId());
	}
	// Session 객체가 삭제 되었을때
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("session destoryed : "+session.getId());
		sessionRepository.remove(session.getId());
	}
	
}












