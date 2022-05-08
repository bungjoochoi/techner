package kr.co.biztechpartners.serveSocket.common.code.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.biztechpartners.serveSocket.admin.vo.CompanyRoleVODT;
import kr.co.biztechpartners.serveSocket.admin.vo.DataTableUserVO;
import kr.co.biztechpartners.serveSocket.user.vo.UserSearchVO;

@Mapper
@Repository
public interface CodeMapper {

    public List<HashMap<String, String>> companyListAjax(String q);
    
    public List<HashMap<String, String>> userListAjax(UserSearchVO userSearchVO);
    
    public List<HashMap<String, String>> userAdminListAjax(UserSearchVO userSearchVO);
    
	public List<HashMap<String, String>>selectCompanyRoleList (String id);
	
	public List<HashMap<String, String>> selectUserRole(Integer id);
	
	public List<HashMap<String, String>> selectCommonCode(String classCode);
	
	public List<HashMap<String, String>> selectClassCode();
	
	public List<HashMap<String, String>> selectSenseMacid();
	
	public int insertCompanyRole(CompanyRoleVODT vo);
	public void updateCompanyRole(Map<String, String> map);
	public void deleteCompanyRole(String id);
	
	public int insertUserRole(DataTableUserVO vo);
	public void updateUserRole(Map<String, String> map);
	public void deleteUserRole(String id);
	
	
	
}
