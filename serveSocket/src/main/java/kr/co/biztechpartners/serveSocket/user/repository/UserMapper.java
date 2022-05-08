package kr.co.biztechpartners.serveSocket.user.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.biztechpartners.serveSocket.admin.vo.DataTableUserVO;
import kr.co.biztechpartners.serveSocket.module.vo.ModuleVO;
import kr.co.biztechpartners.serveSocket.user.vo.UserVO;

@Mapper
@Repository
public interface UserMapper {

	public List<UserVO> selectAllUser();
	
	public List<UserVO> selectCompanyList(List<String> companyName);
	
	public List<UserVO> selectMyCompanyUserList(int id);
	
	public List<UserVO> selectSearchUserList(Map<String, Object> search);
	
	public UserVO selectOneUser(Map<String, Object> search);
	
	public List<HashMap<String, String>> selectUserStatus();	
	
	public int updateUser(UserVO user);
	public int deleteUser(int id);
	public int insertUser(UserVO user);
	
	public List<ModuleVO> selectSearchAdminManager(Map<String, String> searchMap);

	public int selectAllUserCount();

	public int selectSearchUserCount(Map<String, String> searchMap);

	public int insertUserStatus(DataTableUserVO vo);
	public void updateUserStatus(Map<String, String> map);
	public void deleteUserStatus(String id);
}
