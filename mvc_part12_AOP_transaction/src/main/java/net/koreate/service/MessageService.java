package net.koreate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.koreate.dao.MessageDAO;
import net.koreate.dao.UserDAO;
import net.koreate.vo.MessageVO;
import net.koreate.vo.UserVO;

@Service
@RequiredArgsConstructor
public class MessageService {

	private final UserDAO userDAO;
	private final MessageDAO messageDAO;
	
	@Transactional	// 해당 쿼리문 실행시 하나라도 오류 나면  AOP에서 "알아서 rollback" 시켜줌 (AOP 없으면 rollback 안됨)
	// -> 해당 class에 모두 적용 시킬라면 class에 @Transactional달아 줘도 되긴한데 
	// 필요없는거까지 걸어버리면 리소스 낭비가 심하니깐 꼭 필요한 경우에 쓰자.
	// => 메세지를 전송 실패를 해도 point +10 되는걸 Transaction 처리해줘서 point 안오름
	// * auto_increment로 설정한건 rollback이 안되니 참고  -> 이는 동시성때문이라고 함
	public void addMessage(MessageVO vo) throws Exception {
		System.out.println("addMessage Service 호출");
		// 메세지 보내면 발신자 포인트 증가
		UserVO uv = new UserVO();
		uv.setUid(vo.getSender()); // 발신자
		uv.setUpoint(10);
		userDAO.updatePoint(uv);
		System.out.println("uv : " + uv);
		
		// 메세지 등록
		messageDAO.create(vo);
		System.out.println("vo : " + vo);
		System.out.println("addMessage Service 종료");
	}

	public List<MessageVO> list() throws Exception {
		return messageDAO.list();
	}

	public MessageVO readMessage(String uid, int mno) throws Exception{
		
		/* 해당 기능은 부가기능이라서 이거는 AOP로 처리 하는게 좋다
		MessageVO message = messageDAO.readMessage(mno);
		if(message.getOpendate() != null) {
			throw new NullPointerException("이미 읽은 메세지 입니다.");
		}
		*/
		
		// opendate를 현재 시간으로 수정 -> 위 if문에서 opendate 값이 있다는걸 확인 했으니깐
		messageDAO.updateMessage(mno);
		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setUpoint(5);
		userDAO.updatePoint(vo);
		MessageVO message = messageDAO.readMessage(mno);

		return message;
	}
}







