package kr.co.biztechpartners.serveSocket.module.vo;

import java.util.List;

import kr.co.biztechpartners.serveSocket.user.vo.UserVO;

public class ModuleVO {

	private String searchGroupId;
	private Integer mGroupId;
	private	Integer	moduleId;
	private	String	mCode ;
	private	String	mName ;
	private	String	mDesc ;
	private	boolean	isShow ;
	private	int	orderIdx;
	private int mRowspan;
	private int id;
	
	public ModuleVO(int id,String mCode, String mDesc) {
		this.id=id;
		this.mCode=mCode;
		this.mDesc=mDesc;
	}
	
	public ModuleVO() {};
	
	private List<UserVO> userList;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSearchGroupId() {
		return searchGroupId;
	}

	public void setSearchGroupId(String searchGroupId) {
		this.searchGroupId = searchGroupId;
	}

	public Integer getmGroupId() {
		return mGroupId;
	}

	public void setmGroupId(Integer mGroupId) {
		this.mGroupId = mGroupId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmDesc() {
		return mDesc;
	}

	public void setmDesc(String mDesc) {
		this.mDesc = mDesc;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public int getOrderIdx() {
		return orderIdx;
	}

	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}

	public int getmRowspan() {
		return mRowspan;
	}

	public void setmRowspan(int mRowspan) {
		this.mRowspan = mRowspan;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	
	
}
