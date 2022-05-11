package kr.co.techner.serveSocket.common.file.payload;

import java.util.Date;

public class UploadFileResponse {
	
	private Long id;
	private int noticeId;
	private int csrId;
	private int replyId;
	
	private int appFileId;
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
	Date dateCreated;
	
	public UploadFileResponse() {
		
	}
	
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
	
	

	public int getAppFileId() {
		return appFileId;
	}

	public void setAppFileId(int appFileId) {
		this.appFileId = appFileId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getCsrId() {
		return csrId;
	}

	public void setCsrId(int csrId) {
		this.csrId = csrId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}


}
