package kr.co.biztechpartners.serveSocket.admin.vo;

public class CompanyRoleVODT {
	private int id;
	private String roleName;
	public CompanyRoleVODT(int id, String roleName) {
		this.id=id;
		this.roleName=roleName;
	}
	public CompanyRoleVODT() {};
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
