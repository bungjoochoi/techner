package kr.co.biztechpartners.serveSocket.module.vo;

public class UserModuleVO {
	
	private	int	usrMId;
	private	boolean	isMain = false;
	private	boolean	isCustomer = false;
	private	int	moduleId;
	private	int	userId;
	
	private ModuleVO module;
	private ModuleGroupVO moduleGroup;
	
	
	public int getUsrMId() {
		return usrMId;
	}
	public void setUsrMId(int usrMId) {
		this.usrMId = usrMId;
	}
	public boolean isMain() {
		return isMain;
	}
	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}
	public boolean isCustomer() {
		return isCustomer;
	}
	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ModuleVO getModule() {
		return module;
	}
	public void setModule(ModuleVO module) {
		this.module = module;
	}
	public ModuleGroupVO getModuleGroup() {
		return moduleGroup;
	}
	public void setModuleGroup(ModuleGroupVO moduleGroup) {
		this.moduleGroup = moduleGroup;
	}

}
