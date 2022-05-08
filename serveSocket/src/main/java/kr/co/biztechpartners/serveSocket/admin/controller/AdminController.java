package kr.co.biztechpartners.serveSocket.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.biztechpartners.serveSocket.admin.service.AdminService;
import kr.co.biztechpartners.serveSocket.admin.vo.AdminSearchVO;
import kr.co.biztechpartners.serveSocket.admin.vo.CommonCode;
import kr.co.biztechpartners.serveSocket.admin.vo.CommonCodeExt;
import kr.co.biztechpartners.serveSocket.admin.vo.CompanyRoleVODT;
import kr.co.biztechpartners.serveSocket.admin.vo.DataTableUserVO;
import kr.co.biztechpartners.serveSocket.admin.vo.GatewayMst;
import kr.co.biztechpartners.serveSocket.admin.vo.GatewayMstExt;
import kr.co.biztechpartners.serveSocket.admin.vo.IotMst;
import kr.co.biztechpartners.serveSocket.admin.vo.IotMstExt;
import kr.co.biztechpartners.serveSocket.admin.vo.SenseMst;
import kr.co.biztechpartners.serveSocket.admin.vo.SenseMstExt;
import kr.co.biztechpartners.serveSocket.admin.vo.UserVODT;
import kr.co.biztechpartners.serveSocket.common.code.service.CodeService;
import kr.co.biztechpartners.serveSocket.common.util.QueryUtil;
import kr.co.biztechpartners.serveSocket.company.service.CompanyService;
import kr.co.biztechpartners.serveSocket.company.vo.CompanyRoleVO;
import kr.co.biztechpartners.serveSocket.company.vo.CompanySearchVO;
import kr.co.biztechpartners.serveSocket.company.vo.CompanyVO;
import kr.co.biztechpartners.serveSocket.module.vo.ModuleVO;
import kr.co.biztechpartners.serveSocket.user.constant.User;
import kr.co.biztechpartners.serveSocket.user.service.UserService;
import kr.co.biztechpartners.serveSocket.user.vo.UserVO;

@Controller
public class AdminController {
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	@Autowired
	CompanyService companyService;
	@Autowired
	CodeService codeService;

	
	//공통코드 관리
	@GetMapping("/admin/commonCode")
	public ModelAndView commonCodeView(HttpSession session){
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		
		if( myInfo.getRole() != 6) {
			return new ModelAndView("redirect:/main");					
		}
		ModelAndView view = new ModelAndView();
		
		view.setViewName("admin/commonCode");
		return view;
	}
	
	/* 공통코드 관리  */
	@RequestMapping(value="/admin/commonCodeList", method = RequestMethod.POST)
	public @ResponseBody Object commonCodeList(HttpServletRequest request,
            HttpServletResponse response, 
            @RequestParam boolean _search,
            @RequestParam long nd, 
            @RequestParam int rows,
            @RequestParam int page, 
            @RequestParam String sidx,
            @RequestParam String sord,
            @RequestParam(required = false)  String filters
	) throws JsonGenerationException,
            JsonMappingException, IOException {

        HashMap<String, Object> params = new HashMap<String, Object>();
        int start = ((page - 1) * rows) + 1;
        int limit = (start + rows) - 1;
        
        
        System.out.println(filters);
        if(filters != null && !filters.equals("")) {
        //System.out.println(filters);
          params.put("filters", QueryUtil.getQueryCondition(filters));
        }
        params.put("start", start);
        params.put("limit", limit);
        

        List<CommonCodeExt> commonCodeExtList = adminService.selectCommonCode(params);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        int totcnt = 0;
        if( !commonCodeExtList.isEmpty() ) {
        	totcnt = commonCodeExtList.get(0).getTotcnt();
        }
        
        double total = (double) totcnt / rows;

        modelMap.put("total", (int) Math.ceil(total));
        modelMap.put("records", totcnt);
        modelMap.put("rows", commonCodeExtList);
        modelMap.put("page", page);
        
        String value = mapper.writeValueAsString(modelMap);

        return value;
    }
	
    @RequestMapping(value = "/admin/commonCodeEdit", method = RequestMethod.POST)
    public String commonCodeEdit(CommonCode commonCode, @RequestParam String oper, HttpSession session) {
    	UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
    	commonCode.setInsOprt(sessionUser.getUsername());
    	commonCode.setUpdOprt(sessionUser.getUsername());
    	
        int resultValue = 0;

        if (oper.equals("edit")) {
            resultValue = adminService.updateCommonCode(commonCode);
        } else if (oper.equals("del")) {
            resultValue = adminService.deleteCommonCode(commonCode);
        } else if (oper.equals("add")) {
            resultValue = adminService.insertCommonCode(commonCode);
        }

        return "/admin/commonCode";
    }
	
	//공통코드 관리
	@GetMapping("/admin/gatewayMst")
	public ModelAndView gatewayMstView(HttpSession session){
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		
		if( myInfo.getRole() != 6) {
			return new ModelAndView("redirect:/main");					
		}
		ModelAndView view = new ModelAndView();
		
		view.setViewName("admin/gatewayMst");
		return view;
	}
	
	/* 공통코드 관리  */
	@RequestMapping(value="/admin/gatewayMstList", method = RequestMethod.POST)
	public @ResponseBody Object gatewayMstList(HttpServletRequest request,
            HttpServletResponse response, @RequestParam boolean _search,
            @RequestParam long nd, @RequestParam int rows,
            @RequestParam int page, @RequestParam String sidx,
            @RequestParam String sord,
            @RequestParam(required = false)  String filters
			) throws JsonGenerationException,
            JsonMappingException, IOException {

        HashMap<String, Object> params = new HashMap<String, Object>();
        int start = ((page - 1) * rows) + 1;
        int limit = (start + rows) - 1;

        System.out.println(filters);
        if(filters != null)
        //System.out.println(filters);
          QueryUtil.getQueryCondition(filters);
        
        params.put("start", start);
        params.put("limit", limit);

        List<GatewayMstExt> gatewayMstExtList = adminService.selectGatewayMst(params);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        int totcnt = 0;
        if( !gatewayMstExtList.isEmpty() ) {
        	totcnt = gatewayMstExtList.get(0).getTotcnt();
        }
        
        double total = (double) totcnt / rows;

        modelMap.put("total", (int) Math.ceil(total));
        modelMap.put("records", totcnt);
        modelMap.put("rows", gatewayMstExtList);
        modelMap.put("page", page);
        
        String value = mapper.writeValueAsString(modelMap);

        return value;
    }
	
    @RequestMapping(value = "/admin/gatewayMstEdit", method = RequestMethod.POST)
    public String gatewayMstEdit(GatewayMst gatewayMst, @RequestParam String oper, HttpSession session) {
    	UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
    	gatewayMst.setInsOprt(sessionUser.getUsername());
    	gatewayMst.setUpdOprt(sessionUser.getUsername());
    	
        int resultValue = 0;

        if (oper.equals("edit")) {
            resultValue = adminService.updateGatewayMst(gatewayMst);
        } else if (oper.equals("del")) {
            resultValue = adminService.deleteGatewayMst(gatewayMst);
        } else if (oper.equals("add")) {
            resultValue = adminService.insertGatewayMst(gatewayMst);
        }

        return "/admin/gatewayMst";
    }
	
	//공통코드 관리
	@GetMapping("/admin/senseMst")
	public ModelAndView senseMstView(HttpSession session){
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		
		if( myInfo.getRole() != 6) {
			return new ModelAndView("redirect:/main");					
		}
		ModelAndView view = new ModelAndView();
		
		view.setViewName("admin/senseMst");
		return view;
	}
	
	/* 공통코드 관리  */
	@RequestMapping(value="/admin/senseMstList", method = RequestMethod.POST)
	public @ResponseBody Object senseMstList(HttpServletRequest request,
            HttpServletResponse response, @RequestParam boolean _search,
            @RequestParam long nd, @RequestParam int rows,
            @RequestParam int page, @RequestParam String sidx,
            @RequestParam String sord) throws JsonGenerationException,
            JsonMappingException, IOException {

        HashMap<String, Object> params = new HashMap<String, Object>();
        int start = ((page - 1) * rows) + 1;
        int limit = (start + rows) - 1;

        params.put("start", start);
        params.put("limit", limit);

        List<SenseMstExt> senseMstExtList = adminService.selectSenseMst(params);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        int totcnt = 0;
        if( !senseMstExtList.isEmpty() ) {
        	totcnt = senseMstExtList.get(0).getTotcnt();
        }
        
        double total = (double) totcnt / rows;

        modelMap.put("total", (int) Math.ceil(total));
        modelMap.put("records", totcnt);
        modelMap.put("rows", senseMstExtList);
        modelMap.put("page", page);
        
        String value = mapper.writeValueAsString(modelMap);

        return value;
    }
	
    @RequestMapping(value = "/admin/senseMstEdit", method = RequestMethod.POST)
    public String senseMstEdit(SenseMst senseMst, @RequestParam String oper, HttpSession session) {
    	UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
    	senseMst.setInsOprt(sessionUser.getUsername());
    	senseMst.setUpdOprt(sessionUser.getUsername());
    	
        int resultValue = 0;

        if (oper.equals("edit")) {
            resultValue = adminService.updateSenseMst(senseMst);
        } else if (oper.equals("del")) {
            resultValue = adminService.deleteSenseMst(senseMst);
        } else if (oper.equals("add")) {
            resultValue = adminService.insertSenseMst(senseMst);
        }

        return "/admin/senseMst";
    }
	
	//공통코드 관리
	@GetMapping("/admin/iotMst")
	public ModelAndView iotMstView(HttpSession session){
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		
		if( myInfo.getRole() != 6) {
			return new ModelAndView("redirect:/main");					
		}
		ModelAndView view = new ModelAndView();
		
		view.setViewName("admin/iotMst");
		return view;
	}
	
	/* 공통코드 관리  */
	@RequestMapping(value="/admin/iotMstList", method = RequestMethod.POST)
	public @ResponseBody Object iotMstList(HttpServletRequest request,
            HttpServletResponse response, @RequestParam boolean _search,
            @RequestParam long nd, @RequestParam int rows,
            @RequestParam int page, @RequestParam String sidx,
            @RequestParam String sord) throws JsonGenerationException,
            JsonMappingException, IOException {

        HashMap<String, Object> params = new HashMap<String, Object>();
        int start = ((page - 1) * rows) + 1;
        int limit = (start + rows) - 1;

        params.put("start", start);
        params.put("limit", limit);

        List<IotMstExt> iotMstExtList = adminService.selectIotMst(params);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        int totcnt = 0;
        if( !iotMstExtList.isEmpty() ) {
        	totcnt = iotMstExtList.get(0).getTotcnt();
        }
        
        double total = (double) totcnt / rows;

        modelMap.put("total", (int) Math.ceil(total));
        modelMap.put("records", totcnt);
        modelMap.put("rows", iotMstExtList);
        modelMap.put("page", page);
        
        String value = mapper.writeValueAsString(modelMap);

        return value;
    }
	
    @RequestMapping(value = "/admin/iotMstEdit", method = RequestMethod.POST)
    public String iotMstEdit(IotMst iotMst, @RequestParam String oper, HttpSession session) {
    	UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
    	iotMst.setInsOprt(sessionUser.getUsername());
    	iotMst.setUpdOprt(sessionUser.getUsername());
    	
        int resultValue = 0;

        if (oper.equals("edit")) {
            resultValue = adminService.updateIotMst(iotMst);
            if (iotMst.getSenseMacid() != null && iotMst.getUseYn().equals("1")) {
            	adminService.updateSenseMstMappingY(iotMst);
            } else {
            	adminService.updateSenseMstMappingN(iotMst);
            }
        } else if (oper.equals("del")) {
            resultValue = adminService.deleteIotMst(iotMst);
            adminService.updateSenseMstMappingN(iotMst);
        } else if (oper.equals("add")) {
            resultValue = adminService.insertIotMst(iotMst);
            if (iotMst.getSenseMacid() != null && iotMst.getUseYn().equals("1")) {
            	adminService.updateSenseMstMappingY(iotMst);
            }
        }

        return "/admin/iotMst";
    }
	
	//유지보수
	@GetMapping("/admin/bizMR")
	public ModelAndView bizMRView(HttpSession session,@ModelAttribute("searchForm") CompanySearchVO companySearchVO){
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);

		if( myInfo.getRole() != 6) {
			return new ModelAndView("redirect:/main");					
		}
		List<UserVO> userList = userService.getAllUser();
		Integer cdCount = userService.getAllUserCount();
		
		String id = null;
		List<HashMap<String, String>> coRole = codeService.getCompanyRoleList(id);
		List<HashMap<String, String>> userRole = codeService.getUserRole(myInfo.getRole());
		List<HashMap<String, String>> userStatus = userService.getUserStatus();
		List<CompanyVO> companyList = companyService.getAllCompany();
		ModelAndView view = new ModelAndView();
		
		
		view.setViewName("admin/mr");
		view.addObject("userList", userList);
		view.addObject("coRole", coRole);
		view.addObject("userRole", userRole);
		view.addObject("userStatus", userStatus);
		view.addObject("cdCount", cdCount);
		view.addObject("companyList", companyList);
		return view;
	}
	
	//code값 유지보수
	@GetMapping("/admin/codeMR")
	public ModelAndView codeMRView(HttpSession session){
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		
		if( myInfo.getRole() != 6) {
			return new ModelAndView("redirect:/main");
		}
		
		ModelAndView view = new ModelAndView();
		
		List<CompanyRoleVO> companyRoleList = adminService.getAllCompanyRole();
		List<HashMap<String, String>> userRole = codeService.getUserRole(myInfo.getRole());
		
		
		view.setViewName("/admin/template/codeMRRow");
		view.addObject("companyRoleList",companyRoleList);
		view.addObject("userRole", userRole);
		
		
		return view;
		
	}

	/* 사용자 유지보수  */
	@RequestMapping(value="/admin/userModTab" , method=RequestMethod.POST)
	@ResponseBody
		public Object userModTab(String type,String type1,@ModelAttribute UserVODT userVO){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		int retId;
		Map<String,Object> retmap = new HashMap<String,Object>();
		List<UserVODT> inList = new ArrayList<UserVODT>();
		
		if(type.equals("insert")) {
			UserVODT vo = new UserVODT();
//			vo.setmCode(userVO.getmCode());
//			vo.setmDesc(userVO.getmDesc());
			if(type1.equals("1")) {
//				vo.setmGroupId(1);
			}else {
//				vo.setmGroupId(2);
			}
			adminService.insertUserDT(vo);
//			inList.add(new UserVODT(userVO.getId(),userVO.getmCode(),userVO.getmDesc()));
			
			retmap.put("data", inList);
		}else if(type.equals("update")) {
			Map<String,String> upmap = new HashMap<String,String>();
			upmap.put("id", String.valueOf(userVO.getId()));
//			upmap.put("mCode", userVO.getmCode());
//			upmap.put("mDesc", userVO.getmDesc());
			adminService.updateUserDT(upmap);
			
//			inList.add(new UserVODT(userVO.getId(),userVO.getmCode(),userVO.getmDesc()));
			retmap.put("data", inList);
			
		}else if(type.equals("delete")) {
			
		}else {
			UserVODT tmpUserVO= new UserVODT();
			tmpUserVO.setCoName(userVO.getCoName());
			List<UserVODT> list = adminService.selectUserDT(tmpUserVO);
			retmap = new HashMap<String,Object>();
			retmap.put("data", list);
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		Object result = retmap;
					
		return result;
	}
	
	
	
	/* 사용자 현황 코드값 - 사용자 권한  */
	@RequestMapping(value="/admin/codeUser3" , method=RequestMethod.POST)
	@ResponseBody
		public Object codeUser3(HttpSession session, String type,@ModelAttribute DataTableUserVO userVO){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		
		int retId;
		Map<String,Object> retmap = new HashMap<String,Object>();
		List<DataTableUserVO> inList = new ArrayList<DataTableUserVO>();
		
		if(type.equals("insert")) {
			DataTableUserVO vo = new DataTableUserVO();
			vo.setText(userVO.getText());
			codeService.insertUserRole(vo);
			inList.add(new DataTableUserVO(userVO.getId(),userVO.getText()));
			
			retmap.put("data", inList);
		}else if(type.equals("update")) {
			Map<String,String> upmap = new HashMap<String,String>();
			upmap.put("id", String.valueOf(userVO.getId()));
			upmap.put("text", userVO.getText());
			codeService.updateUserRole(upmap);
			
			inList.add(new DataTableUserVO(userVO.getId(),userVO.getText()));
			retmap.put("data", inList);
			
		}else if(type.equals("delete")) {
			codeService.deleteUserRole(String.valueOf(userVO.getId()));
			
		}else {
			Map<String,String> map = new HashMap<String,String>();
			
//			List<DataTableUserVO> list = codeService.getUserRole();
			List<HashMap<String, String>> list = codeService.getUserRole(myInfo.getRole());
			
			retmap = new HashMap<String,Object>();
			retmap.put("data", list);
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		Object result = retmap;
					
		return result;
	}
	
	/* 사용자 현황 코드값 - 사용자 권한  */
	@RequestMapping(value="/admin/codeUser4" , method=RequestMethod.POST)
	@ResponseBody
		public Object codeUser4(String type,@ModelAttribute DataTableUserVO userVO){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		int retId;
		Map<String,Object> retmap = new HashMap<String,Object>();
		List<DataTableUserVO> inList = new ArrayList<DataTableUserVO>();
		
		if(type.equals("insert")) {
			DataTableUserVO vo = new DataTableUserVO();
			vo.setText(userVO.getText());
			userService.insertUserStatus(vo);
			inList.add(new DataTableUserVO(userVO.getId(),userVO.getText()));
			
			retmap.put("data", inList);
		}else if(type.equals("update")) {
			Map<String,String> upmap = new HashMap<String,String>();
			upmap.put("id", String.valueOf(userVO.getId()));
			upmap.put("text", userVO.getText());
			userService.updateUserStatus(upmap);
			
			inList.add(new DataTableUserVO(userVO.getId(),userVO.getText()));
			retmap.put("data", inList);
			
		}else if(type.equals("delete")) {
			userService.deleteUserStatus(String.valueOf(userVO.getId()));
			
		}else {
			Map<String,String> map = new HashMap<String,String>();
			
//			List<DataTableUserVO> list = codeService.getUserRole();
			List<HashMap<String, String>> list = userService.getUserStatus();
			
			retmap = new HashMap<String,Object>();
			retmap.put("data", list);
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		Object result = retmap;
					
		return result;
	}
	
	/*  */
	@RequestMapping(value="/admin/codeCom2" , method=RequestMethod.POST)
	@ResponseBody
	public Object codeCom2(String id,String type,String text){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		int retId;
		Map<String,Object> retmap = new HashMap<String,Object>();
		List<CompanyRoleVODT> inList = new ArrayList<CompanyRoleVODT>();
		
		if(type.equals("insert")) {
			CompanyRoleVODT vo = new CompanyRoleVODT();
			vo.setRoleName(text);
			codeService.insertCompanyRole(vo);
			inList.add(new CompanyRoleVODT(vo.getId(),text));
			retmap.put("data", inList);
		}else if(type.equals("update")) {
			Map<String,String> upmap = new HashMap<String,String>();
			upmap.put("id", id);
			upmap.put("roleName", text);
			codeService.updateCompanyRole(upmap);
			
			inList.add(new CompanyRoleVODT(Integer.valueOf(id),text));
			retmap.put("data", inList);
			
		}else if(type.equals("delete")) {
			codeService.deleteCompanyRole(id);
			
		}else {
			List<HashMap<String, String>> list = codeService.getCompanyRoleList(null);
			retmap = new HashMap<String,Object>();
			retmap.put("data", list);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		Object result = retmap;
					
		return result;
	}
	
	
	//사용자 유지보수
	@RequestMapping(value="/admin/bizMR" , method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView actionSearchCSRManager( @ModelAttribute("searchForm")  @Valid AdminSearchVO adminSearchVO){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Map<String, String> searchMap = new HashMap<String, String>();
		
		/* 회사 */
		if ( adminSearchVO.getCompany1() != null ) {
			searchMap.put("company1", adminSearchVO.getCompany1());
		}
		/* 영역구분 */
		if ( adminSearchVO.getModuleGroup1() != null ) {
			searchMap.put("moduleGroup1", adminSearchVO.getModuleGroup1());
		}
		/* 영역 */
		if ( adminSearchVO.getModule1() != null ) {
			searchMap.put("module1", adminSearchVO.getModule1());
		}
		/* 이름 */
		if ( adminSearchVO.getuName1() != null ) {
			searchMap.put("uName1", adminSearchVO.getuName1());
		}
		/* 전화번호 */
		if ( adminSearchVO.getTel1() != null ) {
			searchMap.put("tel1", adminSearchVO.getTel1());
		}
		/* 휴대전화 */
		if ( adminSearchVO.getMobile1() != null ) {
			searchMap.put("mobile1", adminSearchVO.getMobile1());
		}
		/* 사용자권한 */
		if ( adminSearchVO.getUserRole1() != null ) {
			searchMap.put("userRole1", adminSearchVO.getUserRole1());
		}
		/* 사용자상태 */
		if ( adminSearchVO.getUserStatus1() == 1 ) {
			//재직
			searchMap.put("userStatus1", "1");
		}
		else if ( adminSearchVO.getUserStatus1() == 2 ) {
			//퇴직
			searchMap.put("userStatus1", "2");
		}
		else if ( adminSearchVO.getUserStatus1() == 3 ) {
			//사용중지
			searchMap.put("userStatus1", "3");
		}
		

		
		List<ModuleVO> searchAdminManagerList = userService.getSearchAdminManager(searchMap);
		Integer cdCount = userService.getSearchUserCount(searchMap);
		
		ModelAndView view = new ModelAndView();

		view.setViewName("admin/mr");
		
		view.addObject("userList", searchAdminManagerList);
		view.addObject("cdCount", cdCount);

			
		return view;		
	}
	
	//사용자유지보수 저장
	@PostMapping("/admin/userCdSave")
	@ResponseBody
	public String userCdSave(@RequestParam String[] saveArr) {

		int cnt = Integer.parseInt(saveArr[0].toString());
		
		log.debug("저장할 개수 가운트" +cnt);
		
		log.debug("데이터 확인 " + saveArr[1].toString());
		
		  for(int i=1; i<cnt-1; i++) {

			/*
			 * if(check == 1) { String result = userService.updateUserCd(saveArr); }else {
			 * String result = userService.insertUserCd(saveArr); }
			 */
		 }
		 
		return "redirect:/admin/bizMR";
	}
}
