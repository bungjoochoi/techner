package kr.co.techner.serveSocket.company.vo;

public class CompanyRoleVO {

	int coRoleId;
	boolean isCoustomer;
	boolean isDisplay;
	boolean isOutsourcing;
	String lastUpdated;
	String coRoleName;
	
	public int getCoRoleId() {
		return coRoleId;
	}
	public void setCoRoleId(int coRoleId) {
		this.coRoleId = coRoleId;
	}
	public boolean isCoustomer() {
		return isCoustomer;
	}
	public void setCoustomer(boolean isCoustomer) {
		this.isCoustomer = isCoustomer;
	}
	public boolean isDisplay() {
		return isDisplay;
	}
	public void setDisplay(boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
	public boolean isOutsourcing() {
		return isOutsourcing;
	}
	public void setOutsourcing(boolean isOutsourcing) {
		this.isOutsourcing = isOutsourcing;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getCoRoleName() {
		return coRoleName;
	}
	public void setCoRoleName(String coRoleName) {
		this.coRoleName = coRoleName;
	}
	
}
