package kr.co.biztechpartners.serveSocket.admin.vo;

public class IndustryVO {
	private int id;
	private String industry;
	
	public IndustryVO(int id, String industry) {
		this.id=id;
		this.industry = industry;
	}
	public IndustryVO() {};
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
