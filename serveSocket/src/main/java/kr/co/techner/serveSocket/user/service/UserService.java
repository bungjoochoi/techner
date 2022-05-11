package kr.co.techner.serveSocket.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.techner.serveSocket.admin.vo.DataTableUserVO;
import kr.co.techner.serveSocket.module.vo.ModuleVO;
import kr.co.techner.serveSocket.user.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private kr.co.techner.serveSocket.user.repository.UserMapper UserMapper;
	
	public List<UserVO> getAllUser(){
		return UserMapper.selectAllUser();
	}
	
	public List<UserVO> getMyCompanyCustomerList(int id){
		return UserMapper.selectMyCompanyUserList(id);
	}
	
	public List<UserVO> getCompanyUserList(List<String> companyName){
		return UserMapper.selectCompanyList(companyName);
	}
	
	public UserVO getOneUser(int userId) {
		Map<String, Object> search = new HashMap<String, Object>();

		search.put("userId", userId);		
		UserVO user = UserMapper.selectOneUser(search);
		
		return user;
	}
	
	public UserVO getOneUser(String usrName) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("usrName", usrName);
		
		return UserMapper.selectOneUser(search);
	}
	
	public List<UserVO> getSearchUserList(Map<String,Object> search) {
		return UserMapper.selectSearchUserList(search);
	}
	
	public boolean modifyUser(UserVO user) {
		return UserMapper.updateUser(user) > 0;
	}
	
	public boolean clearUser(int id) {
		boolean isDeleteUser = UserMapper.deleteUser(id) > 0;
		if (!isDeleteUser) {
			return false;
		}
		return true;
	}
	
	public UserVO createUser(UserVO user) {
		UserMapper.insertUser(user);
		return user;
	}
	
    public List<HashMap<String, String>> getUserStatus() {
    	return UserMapper.selectUserStatus();
    }
    
    public int insertUserStatus(DataTableUserVO vo) {
    	return UserMapper.insertUserStatus(vo);
    }
    
    public void updateUserStatus(Map<String, String> map) {
    	UserMapper.updateUserStatus(map);
    }
    
    public void deleteUserStatus(String id) {
    	UserMapper.deleteUserStatus(id);
    }
    
    public List<ModuleVO> getSearchAdminManager(Map<String, String> searchMap) {
		return UserMapper.selectSearchAdminManager(searchMap);
	}
    
	public Integer getAllUserCount() {
		return UserMapper.selectAllUserCount();
	}

	public Integer getSearchUserCount(Map<String, String> searchMap) {
		return UserMapper.selectSearchUserCount(searchMap);
	}
}
