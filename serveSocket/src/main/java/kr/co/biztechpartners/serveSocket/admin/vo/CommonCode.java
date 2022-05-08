package kr.co.biztechpartners.serveSocket.admin.vo;

public class CommonCode {
	private String classCode;
	private String classCodeName;
	private String commonCode;
	private String commonCodeName;
	private String useYn;
	private String insOprt;
	private String insDate;
	private String updOprt;
	private String updDate;
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassCodeName() {
		return classCodeName;
	}
	public void setClassCodeName(String classCodeName) {
		this.classCodeName = classCodeName;
	}
	public String getCommonCode() {
		return commonCode;
	}
	public void setCommonCode(String commonCode) {
		this.commonCode = commonCode;
	}
	public String getCommonCodeName() {
		return commonCodeName;
	}
	public void setCommonCodeName(String commonCodeName) {
		this.commonCodeName = commonCodeName;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getInsOprt() {
		return insOprt;
	}
	public void setInsOprt(String insOprt) {
		this.insOprt = insOprt;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getUpdOprt() {
		return updOprt;
	}
	public void setUpdOprt(String updOprt) {
		this.updOprt = updOprt;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	@Override
	public String toString() {
		return "CommonCode [classCode=" + classCode + ", classCodeName=" + classCodeName + ", commonCode=" + commonCode
				+ ", commonCodeName=" + commonCodeName + ", useYn=" + useYn + ", insOprt=" + insOprt + ", insDate="
				+ insDate + ", updOprt=" + updOprt + ", updDate=" + updDate + "]";
	}
}
