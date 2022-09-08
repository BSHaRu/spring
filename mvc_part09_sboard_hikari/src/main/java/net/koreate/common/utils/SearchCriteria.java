package net.koreate.common.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor		// 기본 생성자 추가
@ToString(callSuper=true) // callSuper : 부모 생성자의 toString도 해줌
public class SearchCriteria extends Criteria {
	
	private String searchType;
	private String keyword;
	
	public SearchCriteria(int page, int perPageNum, // 기존 Criteria가 가지고 있는 속성
					String searchType, String keyword) {	// SearchCriteria에서 추가한 속성
		super(page, perPageNum);
		this.searchType = searchType;
		this.keyword = keyword;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
