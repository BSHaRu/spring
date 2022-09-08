package net.koreate.mvc.common.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria extends Criteria{
	
	private String searchType;
	private String keyword;
	
	@Override
	public String toString() {
		return super.toString()+" SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
}
