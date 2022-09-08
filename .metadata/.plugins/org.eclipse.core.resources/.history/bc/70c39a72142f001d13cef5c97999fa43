package com.koreate.betty.domain.member.service;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.koreate.betty.domain.member.dao.SignRepository;
import com.koreate.betty.domain.member.dto.form.SignInForm;
import com.koreate.betty.domain.member.dto.form.SignUpForm;
import com.koreate.betty.domain.member.util.SignHelper;
import com.koreate.betty.domain.member.vo.Member;
import com.koreate.betty.domain.model.CookieConst;
import com.koreate.betty.domain.model.SessionConst;
import com.koreate.betty.global.error.exception.NotFoundIdException;
import com.koreate.betty.infra.email.EmailSender;
import com.koreate.betty.infra.sms.SMSSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

	@Autowired SignRepository signRepository;
	@Autowired EmailSender emailSender;
	@Autowired SMSSender smsSender;
	
	// 회원가입
	public int signUp(SignUpForm form) {
		int result = 0;
		
		String pw = form.getPw();
		String repw = form.getRepw();
		
		if (!pw.equals(repw)) {
			return result;
		}
		
		Member member = form.convertToMember();
				
		result = signRepository.create(member);
		return result;
	}
	
	// 로그인
	public Member signIn(SignInForm form) {
		Member member = form.convertToMember();
		member = signRepository.findOneBySignIn(member);
		return member;
	}
	
	// 아이디 중복 체크
	public boolean checkIdDupl(String id) {
		return isDupl(signRepository.findIdById(id));
	}
	
	// 닉네임 중복 체크
	public boolean checkNicknameDupl(String nick) {
		return isDupl(signRepository.findNicknameByNickname(nick));
	}
	
	// 이메일 중복 체크
	public boolean checkEmailDupl(String email) {
		return isDupl(signRepository.findEmailByEmail(email));
	}
	
	// 전화번호 중복 체크
	public boolean checkPhoneDupl(String phone) {
		return isDupl(signRepository.findPhoneByPhone(phone));
	}
	
	// 아이디 찾기
	public String forgetId(String name, String phone) {
		String id = signRepository.findIdByForget(name, phone);
		if(id == null) { 
			throw new NotFoundIdException(); 
		}
		return id;
	}
	
	// 비밀번호 찾기
	public String forgetPw(String id, String phone) {
		int result = signRepository.findPwByForget(id, phone);
		if(result == 0) {
			throw new NotFoundIdException();
		}
		return null; // generateForgetCode();
	}
	
	public void sendEmail(String email, HttpSession session) {
		String code = SignHelper.makeCodeForEmail(session);
		emailSender.send(email,code);
	}
	
	public Map<String, String> sendSMS(String phone, HttpSession session) {
		String code = SignHelper.makeCodeForSMS(session);
		return smsSender.send(phone,code);
	}
	
	// 중복 검사 결과
	private boolean isDupl(String target) {
		return target == null ? true : false;
	}
}
