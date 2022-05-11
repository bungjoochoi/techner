package kr.co.techner.serveSocket.admin.vo;

public class DataTableUserVO {
	private int id;
	private String text;
	
	public DataTableUserVO(int id, String text) {
		this.id=id;
		this.text=text;
	}

	public DataTableUserVO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	};
	
}
