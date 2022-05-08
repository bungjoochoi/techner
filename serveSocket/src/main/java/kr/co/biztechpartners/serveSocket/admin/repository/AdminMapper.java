package kr.co.biztechpartners.serveSocket.admin.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.biztechpartners.serveSocket.admin.vo.CommonCode;
import kr.co.biztechpartners.serveSocket.admin.vo.CommonCodeExt;
import kr.co.biztechpartners.serveSocket.admin.vo.GatewayMst;
import kr.co.biztechpartners.serveSocket.admin.vo.GatewayMstExt;
import kr.co.biztechpartners.serveSocket.admin.vo.IotMst;
import kr.co.biztechpartners.serveSocket.admin.vo.IotMstExt;
import kr.co.biztechpartners.serveSocket.admin.vo.SenseMst;
import kr.co.biztechpartners.serveSocket.admin.vo.SenseMstExt;
import kr.co.biztechpartners.serveSocket.admin.vo.UserVODT;
import kr.co.biztechpartners.serveSocket.company.vo.CompanyRoleVO;

@Mapper
@Repository
public interface AdminMapper {

	public List<CompanyRoleVO> selectAllCompanyRole();
	
	public int insertUserDT(UserVODT vo);

	public void updateUserDT(Map<String, String> map);

	public void deleteUserDT(String id);
	
	public List<UserVODT> selectUserDT(UserVODT userVo);
	
	public List<CommonCodeExt> selectCommonCode(HashMap<String, Object> hashMap);
	
	public int insertCommonCode(CommonCode commonCode);
	
	public int updateCommonCode(CommonCode commonCode);
	
	public int deleteCommonCode(CommonCode commonCode);
	
	public List<GatewayMstExt> selectGatewayMst(HashMap<String, Object> hashMap);
	
	public int insertGatewayMst(GatewayMst gatewayMst);
	
	public int updateGatewayMst(GatewayMst gatewayMst);
	
	public int deleteGatewayMst(GatewayMst gatewayMst);
	
	public List<SenseMstExt> selectSenseMst(HashMap<String, Object> hashMap);
	
	public int insertSenseMst(SenseMst senseMst);
	
	public int updateSenseMst(SenseMst senseMst);
	
	public int updateSenseMstMappingY(IotMst iotMst);
	
	public int updateSenseMstMappingN(IotMst iotMst);
	
	public int deleteSenseMst(SenseMst senseMst);
	
	public List<IotMstExt> selectIotMst(HashMap<String, Object> hashMap);
	
	public int insertIotMst(IotMst iotMst);
	
	public int updateIotMst(IotMst iotMst);
	
	public int deleteIotMst(IotMst iotMst);
	
}
