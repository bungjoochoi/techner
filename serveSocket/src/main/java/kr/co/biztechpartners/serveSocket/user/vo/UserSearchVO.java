package kr.co.biztechpartners.serveSocket.user.vo;

import java.util.List;

public class UserSearchVO {
	
	private int searchType;
	private String searchKeyword;
	private String searchCompanyId;
	private String searchUName;
	private String moduleGroupId;
	private String moduleId;
	private List<Integer> coId;
	
	
	public String getModuleGroupId() {
		return moduleGroupId;
	}
	public void setModuleGroupId(String moduleGroupId) {
		this.moduleGroupId = moduleGroupId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
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
	public String getSearchCompanyId() {
		return searchCompanyId;
	}
	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}
	public String getSearchUName() {
		return searchUName;
	}
	public void setSearchUName(String searchUName) {
		this.searchUName = searchUName;
	}
	public List<Integer> getCoId() {
		return coId;
	}
	public void setCoId(List<Integer> coId) {
		this.coId = coId;
	}
	
}
