package kr.co.techner.serveSocket.common.file.vo;

public class ImageFile {

	//{"fileName":"\uc81c\ubaa9 \uc5c6\uc74c.png","uploaded":1,"url":"https:\/\/ckeditor.com\/apps\/ckfinder\/userfiles\/images\/%EC%A0%9C%EB%AA%A9%20%EC%97%86%EC%9D%8C.png"}
	private String fileName;
	private int uploaded;
	private String url;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getUploaded() {
		return uploaded;
	}
	public void setUploaded(int uploaded) {
		this.uploaded = uploaded;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
