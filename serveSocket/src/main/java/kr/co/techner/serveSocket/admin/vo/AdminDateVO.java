package kr.co.techner.serveSocket.admin.vo;

public class AdminDateVO {
	
	/**
	 * 담당자가 직접 날짜와 이름을 추가(ex LG창립기념일)
	 */
	private int id;
	private String date;
	private String dayName;
	private String dateCreated;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "AdminDateVO [id=" + id + ", date=" + date + ", dayName=" + dayName + ", dateCreated=" + dateCreated
				+ "]";
	}
	
	
	
	
	
	
	
	
	

}
