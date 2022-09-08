package net.koreate.sec.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import net.koreate.sec.vo.ValidationMemberVO;

/**
 * 
 * 인가가 완료된 사용자 정보
 *
 */

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private ValidationMemberVO member;
	
	// id는 details에서 1차적으로 확인하고 2차로 pw를 여기서 확인해준다.
	public CustomUser(ValidationMemberVO member) {
		super(member.getU_id(), member.getU_pw(), authorities(member.getAuthList()));
		this.member = member;
	}
	
	public CustomUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public static Collection<? extends GrantedAuthority>
			authorities(List<String> authList){
		List<GrantedAuthority> gList = new ArrayList<>();
		for(String auth : authList) {
			gList.add(new SimpleGrantedAuthority(auth));
		}
		
		return gList;
	}
	

}
