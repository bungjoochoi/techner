package kr.co.biztechpartners.serveSocket.admin.vo;

public class UserVODT {
	private int id;
	private String coName; // 회사이름
	private String companyId; // 회사이름
	private String usrName; // 사용자 ID
	private String uName; // 이름
	private String usrNameEn; // 이름(영어)
	private String usrRole; // 사용자 권한(code)
	private String usrRoleName;// 사용자권한(이름)
	private String approverId;// 승인자Id,
	private String approverName;// 승인자 이름,
	private String moduleGroupName;// 영역구분 이름 sap, non-sap
	private String moduleGroupId;// 영역구분 코드(id) 1,2
	private String moduleName;// 영역 이름    pp, abap
	private String moduleId;// 영역 코드(id)
	// 담당자..?
	private String uTitle; // 직책
	private String usrStatus; //사용자 상태
	private String usrStatusName; //사용자 상태
	private String usrDept; //담당 부서
	// 담당업무 명 ?
	private String usrTel; // 전화번호
	private String usrFax; // 팩스
	private String usrMobile; // 휴대전화
	private String usrEmail; 
	private String isAdminKpi;
	private String isAdminCts;
	
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getUsrNameEn() {
		return usrNameEn;
	}
	public void setUsrNameEn(String usrNameEn) {
		this.usrNameEn = usrNameEn;
	}
	public String getUsrRole() {
		return usrRole;
	}
	public void setUsrRole(String usrRole) {
		this.usrRole = usrRole;
	}
	public String getUsrRoleName() {
		return usrRoleName;
	}
	public void setUsrRoleName(String usrRoleName) {
		this.usrRoleName = usrRoleName;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getModuleGroupName() {
		return moduleGroupName;
	}
	public void setModuleGroupName(String moduleGroupName) {
		this.moduleGroupName = moduleGroupName;
	}
	public String getModuleGroupId() {
		return moduleGroupId;
	}
	public void setModuleGroupId(String moduleGroupId) {
		this.moduleGroupId = moduleGroupId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getuTitle() {
		return uTitle;
	}
	public void setuTitle(String uTitle) {
		this.uTitle = uTitle;
	}
	public String getUsrStatus() {
		return usrStatus;
	}
	public void setUsrStatus(String usrStatus) {
		this.usrStatus = usrStatus;
	}
	public String getUsrStatusName() {
		return usrStatusName;
	}
	public void setUsrStatusName(String usrStatusName) {
		this.usrStatusName = usrStatusName;
	}
	public String getUsrDept() {
		return usrDept;
	}
	public void setUsrDept(String usrDept) {
		this.usrDept = usrDept;
	}
	public String getUsrTel() {
		return usrTel;
	}
	public void setUsrTel(String usrTel) {
		this.usrTel = usrTel;
	}
	public String getUsrFax() {
		return usrFax;
	}
	public void setUsrFax(String usrFax) {
		this.usrFax = usrFax;
	}
	public String getUsrMobile() {
		return usrMobile;
	}
	public void setUsrMobile(String usrMobile) {
		this.usrMobile = usrMobile;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public String getIsAdminKpi() {
		return isAdminKpi;
	}
	public void setIsAdminKpi(String isAdminKpi) {
		this.isAdminKpi = isAdminKpi;
	}
	public String getIsAdminCts() {
		return isAdminCts;
	}
	public void setIsAdminCts(String isAdminCts) {
		this.isAdminCts = isAdminCts;
	}
	
}
