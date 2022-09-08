package net.koreate.common.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.koreate.common.session.MySessionEventListener;
import net.koreate.user.dao.BanIPDAO;
import net.koreate.user.vo.BanIPVO;
import net.koreate.user.vo.UserVO;

@Slf4j
public class SignInInterceptor implements HandlerInterceptor {

	@Autowired
	private BanIPDAO dao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
					HttpServletResponse response,
					Object handler)	throws Exception {
		log.info("------ SignIn preHandle Start");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			// session.invalidate(); // session 무효화
			session.removeAttribute("userInfo"); // session은 유지하되 특정 속성값만 삭제
			log.info("SignIn preHandle session : " + session);
		}
		
		String ip = request.getRemoteAddr();
		log.info("preHandle request IP : " + ip);
		BanIPVO banVO = dao.getBanIPVO(ip);
		log.info("preHandle : " + banVO);
		// 등록된 ip가 존재하면 시도 횟수가  >= 5
		if(banVO != null && banVO.getCnt() >= 5) {
			int limit = 1000 * 60 * 30;	// 30분
			log.info("제한 시간 : " + limit);
			long bandate = banVO.getBandate().getTime();
			log.info("ban 시간 : " + bandate);
			long now = System.currentTimeMillis();
			log.info("현재 시간 : " + now);
			long saveTime = limit - (now - bandate);
			log.info("남은 시간 : " + saveTime);
			if(saveTime > 0) {
				log.info("아직 제한 시간 남음");
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				String time = sdf.format(new Date(saveTime));
				log.info("남은 시간 : "+time);
				RequestDispatcher rd
					= request.getRequestDispatcher("/user/signIn");
				request.setAttribute("message", "일정 시간 동안 로그인 할 수 없습니다.");
				// request.setAttribute("time", time);
				request.setAttribute("time", saveTime);
				rd.forward(request, response);
				return false;
			}else {
				log.info("제한 시간 지남");
				dao.removeBanIP(ip);
			}
		}
		
		log.info("------ SignIn preHandle End");
		return true;
	}
	
	@Autowired
	MySessionEventListener mel;

	@Override
	public void postHandle(HttpServletRequest request, 
					HttpServletResponse response, 
					Object handler,
					ModelAndView modelAndView) throws Exception {
		// 아이디와 비밀번호가 일치하는 사용자 정보가 session에 등록 되어 있는지 확인
		log.info("------ SignIn postHandle Start");
		
		String ip = request.getRemoteAddr();
		BanIPVO banVO = dao.getBanIPVO(ip);
		
		/*
		 * HttpSession session = request.getSession(); UserVO vo = (UserVO)
		 * session.getAttribute("userInfo");
		 */
		
		ModelMap map = modelAndView.getModelMap();
		UserVO vo = (UserVO)map.get("userInfo");
		log.info("SignIn postHandle UserVO : " + vo);
		if(vo != null) {
			// 로그인 성공
			HttpSession session = request.getSession();
			boolean result = mel.expireDuplicatedSession(vo.getUid(), session.getId());
			if(result) {
				System.out.println("중복 제거");
			}else {
				System.out.println("첫 로그인");
			}
			session.setAttribute("userInfo", vo);
			if(banVO != null) {
				dao.removeBanIP(ip);
			}
			
			// cookie 체크 확인
			String useCookie = request.getParameter("useCookie");
			log.info("------ useCookie : " + useCookie);
			if(useCookie != null) {
				Cookie cookie = new Cookie("signInCookie",vo.getUid());
				cookie.setMaxAge(60 * 60 * 24 * 15);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}else {
			String message = "";
			int count = 5;
			
			if(banVO == null) {
				System.out.println("최초 실패");
				dao.signInFail(ip);
				count = count - 1;
			}else {
				System.out.println("중복 실패");
				dao.updateBanIPCnt(ip);
				count = count - (banVO.getCnt()+1);
			}
			
			if(count > 0) {
				message = "회원 정보가 일치하지 않습니다. 남은 횟수 : "+count;
			}else {
				message = "너무 많은 시도... 30분 동안 ip가 차단됩니다.";
			}
			
			// 로그인 실패
//			String message = "회원 정보가 일치하지 않음";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("/user/signIn");
		}
		
		log.info("------ SignIn postHandle Start");
	}

	
	
}
