package net.koreate.common.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class SearchPageMaker extends PageMaker {

	@Override // PageMaker에 있는 makeQuery 재정의 하는거임
	public String makeQuery(int page) {
		// super는 PageMaker고,
		// super.getCri()는 Criteria타입인데
		// Criteria에는 검색할 수 있는 기능이 없어.
		// 근데  SearchCriteria는 검색기능이 있고,Criteria를 상속 하고 있으니깐
		// SearchCriteria 타입으로 바꿔준거임
		SearchCriteria sCriteria = (SearchCriteria)super.getCri();
		
		UriComponents uri
		// 처음 들어온 queryParam에는 ?를 붙여주고, 그 다음 queryParam에는 이어지니깐 &를 자동으로 붙여주는 친구
			= UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("perPageNum", sCriteria.getPerPageNum()) // 이친구는 기존 Criteria의 cri를 가져와도 되는데  
			.queryParam("searchType",sCriteria.getSearchType())	// 이왕 변환한거 그냥 변환된 cri를 쓰는거임
			.queryParam("keyword", sCriteria.getKeyword())
			.build();
		String query = uri.toUriString();
		System.out.println(query);
		return query;
	}
	
	
}
