package kr.co.techner.serveSocket.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.techner.serveSocket.company.vo.CompanyVO;

@Service
public class CompanyService {

	@Autowired
	private kr.co.techner.serveSocket.company.repository.CompanyMapper CompanyMapper;
	
	public List<CompanyVO> getAllCompany(){
		return CompanyMapper.selectAllCompany();
	};
	
	public CompanyVO getCompany(int companyCode) {
		Map<String, Object> search = new HashMap<String, Object>();

		search.put("coCode", companyCode);
		System.out.println("111111111111111coCode");
		return CompanyMapper.selectCompany(search);
	}
	
	public CompanyVO getCompanyUser(String id) {
		Map<String, Object> search = new HashMap<String, Object>();

		search.put("coCode", id);
		System.out.println("111111111111111coCode");
		return CompanyMapper.selectCompanyUser(search);
	}
	
	public CompanyVO getCompany(String coCode) {
		
		Map<String, Object> search = new HashMap<String, Object>();

		search.put("coCode", coCode);
		System.out.println("222222222222222222coId");
		return CompanyMapper.selectCompany(search);
	}

	public List<CompanyVO> getSearchCompanyList(Map<String,Object> search){
		return CompanyMapper.selectSearchCompanyList(search);
 	};
 	
	public boolean createCompany(CompanyVO company){
		return CompanyMapper.insertCompany(company) > 0;
	};
	
	public boolean modifyCompany(CompanyVO company){
		return CompanyMapper.updateCompany(company) > 0;
	}

	public int getCompanyCode(Map<String, Object> map) {
		return CompanyMapper.getCompanyCode(map);
	};
    
	
}
