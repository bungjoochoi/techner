package kr.co.techner.serveSocket.company.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import kr.co.techner.serveSocket.user.vo.UserVO;

public class CompanyVO {
	//@NotNull(message = "회사를 선택해 주십시오.")
	private int coId;
	
	@NotNull(message = "회사코드 입력은 필수입니다.")
	//private Integer companyCode;
	private String companyCode;
	@NotBlank(message = "회사명 입력은 필수입니다.")
	@Size(max=20, message="회사명은 20자 이하이어야 합니다.")
	private String coName;
	private String coNameEN;
	private String coDesc;
	private int orderIdx;
	private String businessType; 
	private String businessCondition; 
	private boolean isShow; 
	private int oldCode; 
	private String address;
	private String tel;
	private String fax;
	private String dateCreated;
	private String lastUpdated;
	private int isServiced;
	private int industryId;
	private String industry;
	private String contractMM;
	private String dueDateS;
	private String dueDateE;
	private String contractStatus;
	
	@NotNull(message = "회사 역할 선택은 필수입니다.")
	private Integer roleId;
	private String roleName;
	
	private List<UserVO> userList;
	
	
	
	
	public int getIsServiced() {
		return isServiced;
	}
	public void setIsServiced(int isServiced) {
		this.isServiced = isServiced;
	}
	public int getCoId() {
		return coId;
	}
	public void setCoId(int coId) {
		this.coId = coId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getCoNameEN() {
		return coNameEN;
	}
	public void setCoNameEN(String coNameEN) {
		this.coNameEN = coNameEN;
	}
	public String getCoDesc() {
		return coDesc;
	}
	public void setCoDesc(String coDesc) {
		this.coDesc = coDesc;
	}
	public int getOrderIdx() {
		return orderIdx;
	}
	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessCondition() {
		return businessCondition;
	}
	public void setBusinessCondition(String businessCondition) {
		this.businessCondition = businessCondition;
	}
	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	public int getOldCode() {
		return oldCode;
	}
	public void setOldCode(int oldCode) {
		this.oldCode = oldCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public int getIndustryId() {
		return industryId;
	}
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getContractMM() {
		return contractMM;
	}
	public void setContractMM(String contractMM) {
		this.contractMM = contractMM;
	}
	public String getDueDateS() {
		return dueDateS;
	}
	public void setDueDateS(String dueDateS) {
		this.dueDateS = dueDateS;
	}
	public String getDueDateE() {
		return dueDateE;
	}
	public void setDueDateE(String dueDateE) {
		this.dueDateE = dueDateE;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
}
