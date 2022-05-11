package kr.co.techner.serveSocket.company.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.techner.serveSocket.company.vo.CompanyVO;

@Mapper
@Repository
public interface CompanyMapper {

	
	public List<CompanyVO> selectAllCompany();
	
	public CompanyVO selectCompany(Map<String, Object> search);

	public CompanyVO selectCompanyUser(Map<String, Object> search);
	
	public List<CompanyVO> selectSearchCompanyList(Map<String,Object> search);
	
	public int insertCompany(CompanyVO company);
	
	public int updateCompany(CompanyVO company);

	public int getCompanyCode(Map<String, Object> map);
	
}
