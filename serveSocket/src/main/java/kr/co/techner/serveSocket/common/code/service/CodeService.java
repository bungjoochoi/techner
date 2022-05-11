package kr.co.techner.serveSocket.common.code.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.techner.serveSocket.admin.vo.CompanyRoleVODT;
import kr.co.techner.serveSocket.admin.vo.DataTableUserVO;
import kr.co.techner.serveSocket.common.code.repository.CodeMapper;
import kr.co.techner.serveSocket.user.vo.UserSearchVO;

@Service
public class CodeService {

    @Autowired
    CodeMapper codeMapper;

    public List<HashMap<String, String>> companyListAjax(String q) {
    	return codeMapper.companyListAjax(q);
    }
    
    public List<HashMap<String, String>> userListAjax(UserSearchVO userSearchVO) {
    	return codeMapper.userListAjax(userSearchVO);
    }
    
    //유저 권한이 4이상인 사람이 검색할 때 회사 상관잆어 이름만 검색해도 결과나옴
    public List<HashMap<String, String>> userAdminListAjax(UserSearchVO userSearchVO) {
    	return codeMapper.userAdminListAjax(userSearchVO);
    }
    
    public List<HashMap<String, String>> getCompanyRoleList(String num) {
    	return codeMapper.selectCompanyRoleList(num);
    }
    
    public int insertCompanyRole(CompanyRoleVODT vo) {
    	return codeMapper.insertCompanyRole(vo);
    }
    
    public void updateCompanyRole(Map<String, String> map) {
    	codeMapper.updateCompanyRole(map);
    }
    
    public void deleteCompanyRole(String id) {
    	codeMapper.deleteCompanyRole(id);
    }
    
    public List<HashMap<String, String>> getUserRole(Integer id) {
    	return codeMapper.selectUserRole(id);
    }
    
    public int insertUserRole(DataTableUserVO vo) {
    	return codeMapper.insertUserRole(vo);
    }
    
    public void updateUserRole(Map<String, String> map) {
    	codeMapper.updateUserRole(map);
    }
    
    public void deleteUserRole(String id) {
    	codeMapper.deleteUserRole(id);
    }
    
    public List<HashMap<String, String>> getCommonCode(String classCode) {
    	return codeMapper.selectCommonCode(classCode);
    }
    
    public List<HashMap<String, String>> getClassCode() {
    	return codeMapper.selectClassCode();
    }
    
    public List<HashMap<String, String>> getSenseMacid() {
    	return codeMapper.selectSenseMacid();
    }
}
