package kr.co.techner.serveSocket.company.vo;

import java.util.List;

public class CompanySearchVO {

	private int searchType;
	private String searchKeyword;
	private List<Integer> coId;
	
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public List<Integer> getCoId() {
		return coId;
	}
	public void setCoId(List<Integer> coId) {
		this.coId = coId;
	}

	
}
