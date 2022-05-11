package kr.co.techner.serveSocket.common.security.vo;

import java.util.Date;

public class User {

	public static Integer ACTIVATED = 1;
	public static Integer RETIRED = 2;
	public static Integer DISABLED = 3;
	
	public static Integer READONLY = 1;
	public static Integer CUSTOMER_GENERAL = 2;
	public static Integer CUSTOMER_APPROVER = 3;
	public static Integer GENERAL = 4;
	public static Integer APPROVER = 5;
	public static Integer ADMIN = 6	;
	
	String username;
	//String password
	String uName;
	String userNameEN;
	String tel;
	String mobile;
	String email;
	String fax;
	String uTitle;
	String businessDesc;
	String dept;
		
	User approver;
	
	User respondent;
	
	User delegator;
	
	Integer role = GENERAL;
		
	Integer status = ACTIVATED;
	
	Integer orderIdx;
	
	String priority = "[1,2]";
	
	Module module;		
	
	/* Automatic timestamping of GORM */
	Date	dateCreated;
	Date	lastUpdated;
	
	Date lastLogin;
	
	Boolean tobeAppr;
	Boolean isAdminKpi;	
	Boolean isAdminCts ;
	public static Integer getACTIVATED() {
		return ACTIVATED;
	}
	public static void setACTIVATED(Integer aCTIVATED) {
		ACTIVATED = aCTIVATED;
	}
	public static Integer getRETIRED() {
		return RETIRED;
	}
	public static void setRETIRED(Integer rETIRED) {
		RETIRED = rETIRED;
	}
	public static Integer getDISABLED() {
		return DISABLED;
	}
	public static void setDISABLED(Integer dISABLED) {
		DISABLED = dISABLED;
	}
	public static Integer getREADONLY() {
		return READONLY;
	}
	public static void setREADONLY(Integer rEADONLY) {
		READONLY = rEADONLY;
	}
	public static Integer getCUSTOMER_GENERAL() {
		return CUSTOMER_GENERAL;
	}
	public static void setCUSTOMER_GENERAL(Integer cUSTOMER_GENERAL) {
		CUSTOMER_GENERAL = cUSTOMER_GENERAL;
	}
	public static Integer getCUSTOMER_APPROVER() {
		return CUSTOMER_APPROVER;
	}
	public static void setCUSTOMER_APPROVER(Integer cUSTOMER_APPROVER) {
		CUSTOMER_APPROVER = cUSTOMER_APPROVER;
	}
	public static Integer getGENERAL() {
		return GENERAL;
	}
	public static void setGENERAL(Integer gENERAL) {
		GENERAL = gENERAL;
	}
	public static Integer getAPPROVER() {
		return APPROVER;
	}
	public static void setAPPROVER(Integer aPPROVER) {
		APPROVER = aPPROVER;
	}
	public static Integer getADMIN() {
		return ADMIN;
	}
	public static void setADMIN(Integer aDMIN) {
		ADMIN = aDMIN;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getUserNameEN() {
		return userNameEN;
	}
	public void setUserNameEN(String userNameEN) {
		this.userNameEN = userNameEN;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getuTitle() {
		return uTitle;
	}
	public void setuTitle(String uTitle) {
		this.uTitle = uTitle;
	}
	public String getBusinessDesc() {
		return businessDesc;
	}
	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public User getApprover() {
		return approver;
	}
	public void setApprover(User approver) {
		this.approver = approver;
	}
	public User getRespondent() {
		return respondent;
	}
	public void setRespondent(User respondent) {
		this.respondent = respondent;
	}
	public User getDelegator() {
		return delegator;
	}
	public void setDelegator(User delegator) {
		this.delegator = delegator;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrderIdx() {
		return orderIdx;
	}
	public void setOrderIdx(Integer orderIdx) {
		this.orderIdx = orderIdx;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Boolean getTobeAppr() {
		return tobeAppr;
	}
	public void setTobeAppr(Boolean tobeAppr) {
		this.tobeAppr = tobeAppr;
	}
	public Boolean getIsAdminKpi() {
		return isAdminKpi;
	}
	public void setIsAdminKpi(Boolean isAdminKpi) {
		this.isAdminKpi = isAdminKpi;
	}
	public Boolean getIsAdminCts() {
		return isAdminCts;
	}
	public void setIsAdminCts(Boolean isAdminCts) {
		this.isAdminCts = isAdminCts;
	}	
	
			
}
