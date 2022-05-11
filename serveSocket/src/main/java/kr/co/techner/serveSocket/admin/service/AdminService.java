package kr.co.techner.serveSocket.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.techner.serveSocket.admin.vo.CommonCode;
import kr.co.techner.serveSocket.admin.vo.CommonCodeExt;
import kr.co.techner.serveSocket.admin.vo.GatewayMst;
import kr.co.techner.serveSocket.admin.vo.GatewayMstExt;
import kr.co.techner.serveSocket.admin.vo.IotMst;
import kr.co.techner.serveSocket.admin.vo.IotMstExt;
import kr.co.techner.serveSocket.admin.vo.SenseMst;
import kr.co.techner.serveSocket.admin.vo.SenseMstExt;
import kr.co.techner.serveSocket.admin.vo.UserVODT;
import kr.co.techner.serveSocket.company.vo.CompanyRoleVO;

@Service
public class AdminService {
	@Autowired
	private kr.co.techner.serveSocket.admin.repository.AdminMapper adminMapper;
	
	public List<CompanyRoleVO> getAllCompanyRole() {
		return adminMapper.selectAllCompanyRole();
	}
    
    public List<UserVODT> selectUserDT(UserVODT userVo) {
		return adminMapper.selectUserDT(userVo);
	}
	
	public int insertUserDT(UserVODT vo) {
    	return adminMapper.insertUserDT(vo);
    }
    
    public void updateUserDT(Map<String, String> map) {
    	adminMapper.updateUserDT(map);
    }
    
    public void deleteUserDT(String id) {
    	adminMapper.deleteUserDT(id);
    }
    
    public List<CommonCodeExt> selectCommonCode(HashMap<String, Object> hashMap) {
    	return adminMapper.selectCommonCode(hashMap);
    }
    
    public int insertCommonCode(CommonCode commonCode) {
    	return adminMapper.insertCommonCode(commonCode);
    }
    
    public int updateCommonCode(CommonCode commonCode) {
    	return adminMapper.updateCommonCode(commonCode);
    }
    
    public int deleteCommonCode(CommonCode commonCode) {
    	return adminMapper.deleteCommonCode(commonCode);
    }
    
    public List<GatewayMstExt> selectGatewayMst(HashMap<String, Object> hashMap) {
    	return adminMapper.selectGatewayMst(hashMap);
    }
    
    public int insertGatewayMst(GatewayMst gatewayMst) {
    	return adminMapper.insertGatewayMst(gatewayMst);
    }
    
    public int updateGatewayMst(GatewayMst gatewayMst) {
    	return adminMapper.updateGatewayMst(gatewayMst);
    }
    
    public int deleteGatewayMst(GatewayMst gatewayMst) {
    	return adminMapper.deleteGatewayMst(gatewayMst);
    }
    
    public List<SenseMstExt> selectSenseMst(HashMap<String, Object> hashMap) {
    	return adminMapper.selectSenseMst(hashMap);
    }
    
    public int insertSenseMst(SenseMst senseMst) {
    	return adminMapper.insertSenseMst(senseMst);
    }
    
    public int updateSenseMst(SenseMst senseMst) {
    	return adminMapper.updateSenseMst(senseMst);
    }
    
    public int updateSenseMstMappingY(IotMst iotMst) {
    	return adminMapper.updateSenseMstMappingY(iotMst);
    }
    
    public int updateSenseMstMappingN(IotMst iotMst) {
    	return adminMapper.updateSenseMstMappingN(iotMst);
    }
    
    public int deleteSenseMst(SenseMst senseMst) {
    	return adminMapper.deleteSenseMst(senseMst);
    }
    
    public List<IotMstExt> selectIotMst(HashMap<String, Object> hashMap) {
    	return adminMapper.selectIotMst(hashMap);
    }
    
    public int insertIotMst(IotMst iotMst) {
    	return adminMapper.insertIotMst(iotMst);
    }
    
    public int updateIotMst(IotMst iotMst) {
    	return adminMapper.updateIotMst(iotMst);
    }
    
    public int deleteIotMst(IotMst iotMst) {
    	return adminMapper.deleteIotMst(iotMst);
    }
}
