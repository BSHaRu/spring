package net.koreate.lombok;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

// @Data - 알아서 모두 다 정의해줌
@Getter // - 필드에 개별로 줄수도 있음
@Setter // - 필드에 개별로 줄수도 있음
//@ToString(exclude = "upw") // 하나면 그냥 작성, 2개 이상이면 {}쓰면됨
@ToString(of = {"uno","uname","friendList"}) // toString도 똑같이 이렇게 제어가능
//of={}를 해주면 {}안에 있는 값만 비교해줌
//@EqualsAndHashCode(of= {"uid","upw"})	
//exclude={}는 {}안에 있는 값을 제외하고 비교해줌
@EqualsAndHashCode(exclude = {"uno","regdate","uname","friendList"}) 
//@NoArgsConstructor // 기본생성자 - final 있으면 해당값이 초기화가 안되기때문에 사용 못함
@AllArgsConstructor // 모든 값을 다 넘겨받는 생성자

//@RequiredArgsConstructor(access = AccessLevel.PUBLIC) //필수 인자값  // ()없으면 기본값이 public임
@RequiredArgsConstructor(access = AccessLevel.PACKAGE) 
@Builder 
public class UserVO {

	@Getter
	private int uno;
	@NonNull // not null -> 꼭 필요한 친구
	private String uid;
	@NonNull
	private String upw;
	private final String uname; // final도 필수 인자값에 해당됨
	@Setter
	private Date regdate;
	
	@Singular("list") // Builder 있어야 쓸 수 있는 친구
	private List<String> friendList;
}
