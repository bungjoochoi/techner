package kr.co.biztechpartners.serveSocket.admin.vo;

import java.util.List;

public class AdminSearchVO {
	
	
	/* 회사 */
	private String company1;
	/* 영역구분 */
	private String moduleGroup1;
	/* 영역 */
	private String module1;
	/* 이름 */
	private String uName1;
	/* 전화번호 */
	private String tel1;
	/* 휴대전화 */
	private String mobile1;
	/* 사용자권한 */
	private String userRole1;
	/* 사용자상태 */
	private int userStatus1;
	/* email */
	private String email1;
	private List<Integer> coId;
	
	
	public String getCompany1() {
		return company1;
	}
	public void setCompany1(String company1) {
		this.company1 = company1;
	}
	public String getModuleGroup1() {
		return moduleGroup1;
	}
	public void setModuleGroup1(String moduleGroup1) {
		this.moduleGroup1 = moduleGroup1;
	}
	public String getModule1() {
		return module1;
	}
	public void setModule1(String module1) {
		this.module1 = module1;
	}
	public String getuName1() {
		return uName1;
	}
	public void setuName1(String uName1) {
		this.uName1 = uName1;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getUserRole1() {
		return userRole1;
	}
	public void setUserRole1(String userRole1) {
		this.userRole1 = userRole1;
	}
	public int getUserStatus1() {
		return userStatus1;
	}
	public void setUserStatus1(int userStatus1) {
		this.userStatus1 = userStatus1;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public List<Integer> getCoId() {
		return coId;
	}
	public void setCoId(List<Integer> coId) {
		this.coId = coId;
	}
	

	
}
