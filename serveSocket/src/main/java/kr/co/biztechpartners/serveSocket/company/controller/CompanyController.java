package kr.co.biztechpartners.serveSocket.company.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.biztechpartners.serveSocket.common.security.service.AuthService;
import kr.co.biztechpartners.serveSocket.common.security.vo.SecurityUser;
import kr.co.biztechpartners.serveSocket.company.service.CompanyService;
import kr.co.biztechpartners.serveSocket.company.vo.CompanySearchVO;
import kr.co.biztechpartners.serveSocket.company.vo.CompanyVO;
import kr.co.biztechpartners.serveSocket.user.constant.User;
import kr.co.biztechpartners.serveSocket.user.service.UserService;
import kr.co.biztechpartners.serveSocket.user.vo.UserSearchVO;
import kr.co.biztechpartners.serveSocket.user.vo.UserVO;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {

	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	CompanyService companyService;
	@Autowired
	UserService userService;
	@Autowired
    AuthService authService;
	
	@GetMapping("/list")
	public ModelAndView showCompanyList(HttpSession session) {
		//user정보를 session에서 가져와 받아서 권한 확인
		UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
		ModelAndView view = new ModelAndView();
		
		List<CompanyVO> companyList = companyService.getAllCompany();
		
		if( sessionUser.getCompany().getRoleId() == 1) {
			//biztech : 전체회사 다
			companyList = companyService.getAllCompany();

			view.addObject("companyList", companyList);
			view.setViewName("company/list");		
			return view;
		}
		else {
			//customer : 자기 회사만
			CompanyVO company = companyService.getCompany(sessionUser.getCompany().getCompanyCode());
			
			companyList = new ArrayList<CompanyVO>();
			companyList.add(company);
			
			view.addObject("companyList", companyList);
			view.setViewName("company/list");		
			return view;
		}		
	}
	
	//TODO ajax로 바꾸기
	@GetMapping("/view/{id}")
	public ModelAndView showCompanyDetail(@PathVariable String id, HttpSession session) {
		ModelAndView view = new ModelAndView();
		UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
		
		
		//log.debug("확인이라능" + company.isServiced());
		if ( sessionUser.getCompany().getRoleId() == 2 ) {
			//customer : 자기 회사만
			if( !sessionUser.getCompany().getCompanyCode().equals(id) ) {
				return new ModelAndView("redirect:/company/list");
			}
		}
		else if(sessionUser.getCompany().getRoleId() == 3){
			//customer : 자기 회사만
			if( !sessionUser.getCompany().getCompanyCode().equals(id) ) {
				return new ModelAndView("redirect:/company/list");
			}
		}
		CompanyVO company = companyService.getCompanyUser(id);
		view.addObject("company", company);
		view.setViewName("company/view");
		
		return view;
	}
	
    @GetMapping("/modify/{companyCode}")
	public ModelAndView showModifyCompany(@PathVariable String companyCode, HttpSession session) {
    	UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
		
		if( sessionUser.getRole() != 6) {
			return new ModelAndView("redirect:/company/list");
		}
		
		ModelAndView view = new ModelAndView();
		CompanyVO oldCo = companyService.getCompany(companyCode);		
		
		view.addObject("company", oldCo);
		view.setViewName("company/template/modify");
		
		return view;
	}
    
    @PostMapping(value="/modify/{companyCode}")
    @ResponseBody
	public String modifyCompany(@ModelAttribute("modifyForm") @RequestBody @Valid CompanyVO company, 
			@PathVariable String companyCode, HttpSession session) {
    	
    	UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
				
		if( sessionUser.getRole() != 6) {
			return "redirect:/company/list";
		}
		
		CompanyVO oldCo = companyService.getCompany(companyCode);
		
		if( oldCo.getCoName() != company.getCoName() ) {
			oldCo.setCoName(company.getCoName());
		}
		
		if( oldCo.getCoNameEN() != company.getCoNameEN() ) {
			oldCo.setCoNameEN(company.getCoNameEN());
		}
		
		if( oldCo.getCompanyCode() != company.getCompanyCode() ) {
			oldCo.setCompanyCode(company.getCompanyCode());
		}
		
		if( oldCo.getCoDesc() != company.getCoDesc() ) {
			oldCo.setCoDesc(company.getCoDesc());
		}
		
		if( oldCo.getBusinessType() != company.getBusinessType() ) {
			oldCo.setBusinessType(company.getBusinessType());
		}
		
		if( oldCo.getBusinessCondition() != company.getBusinessCondition() ) {
			oldCo.setBusinessCondition(company.getBusinessCondition());
		}
		
		if( oldCo.getAddress() != company.getAddress() ) {
			oldCo.setAddress(company.getAddress());
		}
		
		if( oldCo.getTel() != company.getTel() ) {
			oldCo.setTel(company.getTel());
		}
		
		if( oldCo.getFax() != company.getFax() ) {
			oldCo.setFax(company.getFax());
		}
		
		if( oldCo.getCoNameEN() != company.getCoNameEN() ) {
			oldCo.setCoNameEN(company.getCoNameEN());
		}
		
		if( oldCo.getIsServiced() != company.getIsServiced() ) {
			oldCo.setIsServiced(company.getIsServiced());
		}
		
		if( oldCo.getIndustryId() != company.getIndustryId() ) {
			oldCo.setIndustryId(company.getIndustryId());
		}
		
		if( oldCo.getRoleId() != company.getRoleId() ) {
			oldCo.setRoleId(company.getRoleId());
		}
		
		if( oldCo.getContractMM() != company.getContractMM() ) {
			oldCo.setContractMM(company.getContractMM());
		}
		
		if( oldCo.getDueDateS() != company.getDueDateS() ) {
			oldCo.setDueDateS(company.getDueDateS());
		}
		
		if( oldCo.getDueDateE() != company.getDueDateE()) {
			oldCo.setDueDateE(company.getDueDateE());
		}
		
		if( oldCo.getContractStatus() != company.getContractStatus() ) {
			oldCo.setContractStatus(company.getContractStatus());
		}
		
		if ( companyService.modifyCompany(oldCo) ) {
			oldCo = companyService.getCompany(companyCode);
			
			//ModelAndView view = new ModelAndView();
			//view.addObject("company", oldCo);
			//view.setViewName("company/template/modify");
			
			return "/company/view/" + company.getCompanyCode();
		}		
				
		return "/company/list";
	}
	
	@GetMapping("/create")
	public ModelAndView showCreateCompany() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		ModelAndView view = new ModelAndView();
		view.setViewName("company/template/create");
		
		return view;
	}
	
	@PostMapping("/create")
	@ResponseBody
	public String createCompany(@ModelAttribute("modifyForm") @RequestBody @Valid CompanyVO company, HttpSession session) {
		UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
//		log.debug(company.getCoName());		
//		log.debug(company.getIsServiced());		
//		log.debug(company.getRoleId());	
		
		if( sessionUser.getRole() != 6) {
			return "redirect:/company/list";
		}		
		
		log.debug("여기까지 왓니~~~?");
		
		//ModelAndView view = new ModelAndView();
		
		log.debug("확인1 : "+company.getBusinessCondition());
		log.debug("확인2 : "+company.getBusinessType());
		
		if(company.getCoId() == 0 ) {
			//회사생성
			companyService.createCompany(company);
		}
		
		//view.setViewName("company/companyRoleSelect");
		//return view;
		return "/company/view/" + company.getCompanyCode();
		//return null;
	}
	
	
	//사용자 찾기
	@RequestMapping(value="/searchCompanyListAjax", method = RequestMethod.GET)
	public ModelAndView searchUser( @ModelAttribute("searchForm")  @Valid CompanySearchVO coSearch ){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		if ( coSearch.getSearchType() == 0 ) {
			//전체검색
			searchMap.put("searchType", "companyCode");
		}
		else if ( coSearch.getSearchType() == 1 ) {
			searchMap.put("searchType", "coId");
		}
		else if ( coSearch.getSearchType() == 2 ) {
			searchMap.put("searchType", "roleId");
		}
		else if ( coSearch.getSearchType() == 3 ) {
			searchMap.put("searchType", "industryId");
		}
		
		if ( coSearch.getSearchKeyword() != null ) {
			searchMap.put("keyword", coSearch.getSearchKeyword());
		}
		else {
			searchMap.put("keyword", "all");
		}
		
		log.debug("확인:"+coSearch.getCoId());
		if ( coSearch.getCoId() != null ) {
			searchMap.put("list", coSearch.getCoId());
		}
		List<CompanyVO> searchCompanyList = companyService.getSearchCompanyList(searchMap);
		
		Map<Object,Object> searchBack = new HashMap<Object, Object>();
		if ( coSearch.getSearchType() == 2 ) {
			searchBack.put( "roleName", searchCompanyList.get(0).getRoleName());
		}
		else if ( coSearch.getSearchType() == 3 ) {
			searchBack.put( "industry", searchCompanyList.get(0).getIndustry());
		}	
		
		ModelAndView view = new ModelAndView();

		view.setViewName("company/list");
		view.addObject("companyList", searchCompanyList);
		view.addObject("coSearch",coSearch);
		view.addObject("searchBack",searchBack);
				
		return view;		
	}
	
	//사용자 현황리스트의 회사selector를 가져오는 ajax
	//outsourcingd은 자기 회사와 고객사만 볼 수 있음
	//customer들은 자기회사만 볼 수 있음
	@RequestMapping(value="/smkpiCompanyListAjax")
	@ResponseBody
	public HashMap<String, Object> smkpiCompanyListAjax(HttpSession session, String q){
		List<CompanyVO> readCoList = null;
		List<HashMap<String, Object>> companyList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> companys = null;
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		log.debug("입력받은값 : "+q);
			//biztech 모든 회사를 다 가져옴
		readCoList = companyService.getAllCompany();			
		for(CompanyVO company : readCoList) {
			if (company.getRoleId() == 3) {
				companys = new HashMap<String, Object>();
				if( q != null ) {
					if ( company.getCoName().contains(q)) {
						companys.put("id", company.getCoId());
						companys.put("text", company.getCoName());
						companyList.add(companys);
					}
				}
				else {
					companys.put("id", company.getCoId());
					companys.put("text", company.getCoName());
					companyList.add(companys);
				}
			}
		}			
		rtn.put("result", companyList);
		return rtn;
	}	
	
}
