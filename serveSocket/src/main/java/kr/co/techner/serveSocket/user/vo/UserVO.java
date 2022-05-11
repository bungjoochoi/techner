package kr.co.techner.serveSocket.user.vo;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import kr.co.techner.serveSocket.common.security.vo.SecUser;
import kr.co.techner.serveSocket.company.vo.CompanyVO;

/**
 * @author test
 *
 */
public class UserVO {

	private Integer userId;
	
	@NotBlank(message = "사용자ID는 필수 입력값입니다.")
	private String username;
	
	@NotBlank(message = "사용자명은 필수 입력값입니다.")
	private String uName;
	
	private int coId;
	
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message="비밀번호는 8자 이상의 문자+숫자+특수문자 조합이어야 합니다.")
	private String password;
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message="비밀번호 확인은 8자 이상의 문자+숫자+특수문자 조합이어야 합니다.")
	private String passwordConfirm;
	@AssertTrue(message="비밀번호 확인결과가 다릅니다. 비밀번호를 다시 확인해주십시오.")
	private boolean pass;
	private String userNameEN;
	private String uTitle;
	@NotBlank(message = "사무실 전화는 필수 입력값입니다.")
	private String tel;
	@NotBlank(message = "휴대전화는 필수 입력값입니다.")
	private String mobile;
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "이메일 형식으로 입력해 주십시오.")
	private String email;
	private int orderIdx;
	private String businessDesc;
	private String fax;
	private int role;
	private String roleName;
	private int status;
	private String dept;
	
	private String dateCreated;
	private String lastUpdated;
	
	private SecUser secUser;
	
	@NotNull(message = "회사를 선택해 주십시요.")
	private CompanyVO company;
	
	private int isCustomer;
	private String coName;
	private String mName;
	private int userCdCnt;
	private int groupId;
	private String groupName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public int getCoId() {
		return coId;
	}
	public void setCoId(int coId) {
		this.coId = coId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public String getUserNameEN() {
		return userNameEN;
	}
	public void setUserNameEN(String userNameEN) {
		this.userNameEN = userNameEN;
	}
	public String getuTitle() {
		return uTitle;
	}
	public void setuTitle(String uTitle) {
		this.uTitle = uTitle;
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
	public int getOrderIdx() {
		return orderIdx;
	}
	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}
	public String getBusinessDesc() {
		return businessDesc;
	}
	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	public SecUser getSecUser() {
		return secUser;
	}
	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}
	public CompanyVO getCompany() {
		return company;
	}
	public void setCompany(CompanyVO company) {
		this.company = company;
	}
	public int getIsCustomer() {
		return isCustomer;
	}
	public void setIsCustomer(int isCustomer) {
		this.isCustomer = isCustomer;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getUserCdCnt() {
		return userCdCnt;
	}
	public void setUserCdCnt(int userCdCnt) {
		this.userCdCnt = userCdCnt;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
