package net.koreate.comment.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
DAO(Data Access Object) - 데이터를 저장하는 객체
	-> 추상 인터페이스 제공하는 객체 / DB의 세부 내용을 노출시키지 않고 특정 데이터 조작 가능
DTO(Data Transfer Object) - 데이터 전달을 위한 객체(읽기 | 쓰기가능) / 수집 용도가 좀 더 강함
VO(Value Object) - 변하지 않는 데이터들의 객체 (읽기 전용)
 * 
 */
@Getter
@Setter
@ToString
public class CommentDTO {
	
	private int cno;			// 댓글번호
	private int bno;			// 댓글이 작성된 게시글 번호
	private String commentAuth;	// 작성자
	private String commentText;	// 댓글 내용
	private Date regdate;		// 등록 시간
	private Date updatedate;	// 수정 시간
	
	
	
	
	
	
	
	
	
	
}
