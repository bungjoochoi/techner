package kr.co.techner.serveSocket.user.controller;


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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import kr.co.techner.serveSocket.common.code.service.CodeService;
import kr.co.techner.serveSocket.common.security.service.AuthService;
import kr.co.techner.serveSocket.common.security.vo.SecUser;
import kr.co.techner.serveSocket.common.security.vo.SecurityUser;
import kr.co.techner.serveSocket.company.service.CompanyService;
import kr.co.techner.serveSocket.company.vo.CompanyVO;
import kr.co.techner.serveSocket.user.constant.User;
import kr.co.techner.serveSocket.user.service.UserService;
import kr.co.techner.serveSocket.user.vo.UserSearchVO;
import kr.co.techner.serveSocket.user.vo.UserVO;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	@Autowired
    AuthService authService;
	@Autowired
    CompanyService companyService;
	@Autowired
	CodeService codeService;
	
	//사용자 현황 main리스트
	@GetMapping("/list")
	public ModelAndView showUserList(HttpSession session){
		//user정보를 session에서 가져와 받아서 권한 확인
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		
		ModelAndView view = new ModelAndView();		
		
		// user는 권한 상관없이 자기 회사의 user정보만 가져옴 - 회사코드로조회
		List<UserVO> userList = userService.getMyCompanyCustomerList(searchUser.getCompany().getCoId());
		
		view.setViewName("user/list");
		view.addObject("userList", userList);
				
		return view;
	}
	
	//사용자 현황 - 회사 변경 용 ajax
	@RequestMapping(value="/listSearchCompany" , method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView showUserListAjax(@RequestBody Map<String, Object> sendMap,HttpSession session){
		List<String> companyName = new ArrayList<String>();
		List<Map<String, String>> paramMap = null ;
		
		System.out.println(sendMap);
		
		if ( sendMap.get("multiparam") != null ) {
			paramMap = (List<Map<String, String>>) sendMap.get("multiparam");
			for(Map<String, String> map : paramMap) {
				companyName.add(map.get("cName"));			
			}			
		}

		List<UserVO> userList = null;
		
		if( companyName.size() != 0 ) {
			userList = userService.getCompanyUserList(companyName);
		}
		else {
			userList = new ArrayList<UserVO>();
		}		
		
		ModelAndView view = new ModelAndView();

		view.setViewName("user/template/listRow");
		view.addObject("userList", userList);
				
		return view;
	}
	
	//사용자  조회조건으로 찾기
	@RequestMapping(value="/listAjax", method = RequestMethod.GET)
	public ModelAndView actionSearchUser(HttpSession session, @ModelAttribute("searchForm")  @Valid UserSearchVO userSearch ){
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		if ( userSearch.getSearchType() == 0 ) {
			//전체검색
			searchMap.put("searchType", "uName");
		}
		else if ( userSearch.getSearchType() == 1 ) {
			searchMap.put("searchType", "uTitle");
		}
		else if ( userSearch.getSearchType() == 2 ) {
			searchMap.put("searchType", "tel");
		}
		else if ( userSearch.getSearchType() == 3 ) {
			searchMap.put("searchType", "email");
		}
		if ( userSearch.getSearchKeyword() != null ) {
			searchMap.put("keyword", userSearch.getSearchKeyword());
		}
		else {
			searchMap.put("keyword", "all");
		}
		
		/*
		 * 회사 권한 별 조건 처리(전체)조회 때문
		 */
		List<CompanyVO> readCoList = null;
		List<Integer> co = null;
		if ( userSearch.getCoId()!= null && userSearch.getCoId().size() != 0 ) {
			if ( searchUser.getCompany().getRoleId() == 2 ) {
				for(int i = 0; i<userSearch.getCoId().size() ; i++) {
					if(userSearch.getCoId().get(i) != searchUser.getCompany().getCoId()) {
						return new ModelAndView("redirect:/user/list");
					}
				}
			} else if(searchUser.getCompany().getRoleId() == 3) {
				for(int i = 0; i<userSearch.getCoId().size() ; i++) {
					if(userSearch.getCoId().get(i) != searchUser.getCompany().getCoId()) {
						return new ModelAndView("redirect:/user/list");
					}
				}
			}
			
			searchMap.put("list", userSearch.getCoId());
		}
		else {
			if( searchUser.getCompany().getRoleId() == 2 ) {
				co = new ArrayList<Integer>();
				co.add(searchUser.getCompany().getCoId());
				userSearch.setCoId(co);
				searchMap.put("list", userSearch.getCoId());
			}
			else if(searchUser.getCompany().getRoleId() == 3) {
				co = new ArrayList<Integer>();
				co.add(searchUser.getCompany().getCoId());
				userSearch.setCoId(co);
				searchMap.put("list", userSearch.getCoId());
			}
		}
		
		List<UserVO> searchUserList = userService.getSearchUserList(searchMap);
		List<Map<String, Object>> companyList = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = null;
		boolean isNotNull = false;
		
		if ( userSearch.getCoId() != null ) {
			for ( UserVO users : searchUserList) {
				if ( companyList.size() == 0) {
					map = new HashMap<String, Object>();
					map.put("id", users.getCompany().getCoId());
					map.put("text", users.getCompany().getCoName());
					companyList.add(map);
				}
				else {
					for(Map<String, Object> companys : companyList) {
						int id = (Integer) companys.get("id");
						if( id != users.getCompany().getCoId() ) {
							isNotNull = true;
						}
						else {
							isNotNull = false;
						}
					}
					if (isNotNull) {
						map = new HashMap<String, Object>();
						map.put("id", users.getCompany().getCoId());
						map.put("text", users.getCompany().getCoName());
						companyList.add(map);
					}
				}			
			}	
		}
		else {
			map = new HashMap<String, Object>();
			map.put("id", "0");
			map.put("text", "---전체---" );
			companyList.add(map);
		}
				
		ModelAndView view = new ModelAndView();

		view.setViewName("user/list");
		view.addObject("userList", searchUserList);
		view.addObject("companyList", companyList);
		view.addObject("userSearch", userSearch);
				
		return view;	
	}

	//사용자 현황리스트의 회사selector를 가져오는 ajax
	//outsourcingd은 자기 회사와 고객사만 볼 수 있음
	//customer들은 자기회사만 볼 수 있음
	@RequestMapping(value="/companySelectorListAjax")
	@ResponseBody
	public HashMap<String, Object> companyListAjax(HttpSession session, String q){
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		log.debug("------------==============여기들어옴?=====================");
		List<CompanyVO> readCoList = null;
		List<HashMap<String, Object>> companyList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> companys = null;
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		log.debug("입력받은값 : "+q);
		if( searchUser.getCompany().getRoleId() == 1) {
			//biztech 모든 회사를 다 가져옴
			readCoList = companyService.getAllCompany();			
			for(CompanyVO company : readCoList) {
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
			rtn.put("result", companyList);
			return rtn;
		}
		else {
			//사용자가 customer면 자기 회사의 사용자만 볼 수 있음
			companys = new HashMap<String, Object>();
			companys.put("id", searchUser.getCompany().getCoId());
			companys.put("text", searchUser.getCompany().getCoName());
			companyList.add(companys);
					
			rtn.put("result", companyList);
			return rtn;
		}	
	}	
	
	
	//사용자 현황리스트의 회사selector를 가져오는 ajax
	//outsourcingd은 자기 회사와 고객사만 볼 수 있음
	//customer들은 자기회사만 볼 수 있음
	@RequestMapping(value="/coSelectorListAjax")
	@ResponseBody
	public HashMap<String, Object> coListAjax(HttpSession session, String q){
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		log.debug("------------==============여기들어옴?=====================");
		List<CompanyVO> readCoList = null;
		List<HashMap<String, Object>> companyList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> companys = null;
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		log.debug("입력받은값 : "+q);
		if( searchUser.getCompany().getRoleId() == 1) {
			//biztech 모든 회사를 다 가져옴
			readCoList = companyService.getAllCompany();			
			for(CompanyVO company : readCoList) {
				companys = new HashMap<String, Object>();
				if( q != null ) {
					if ( company.getCoName().contains(q)) {
						companys.put("id", company.getCompanyCode());
						companys.put("text", company.getCoName());
						companys.put("etc", company.getCoId());
						companyList.add(companys);
					}
				}
				else {
					companys.put("id", company.getCompanyCode());
					companys.put("text", company.getCoName());
					companys.put("etc", company.getCoId());
					companyList.add(companys);
				}
			}			
			rtn.put("result", companyList);
			return rtn;
		}
		else {
			//사용자가 customer면 자기 회사의 사용자만 볼 수 있음
			companys = new HashMap<String, Object>();
			companys.put("id", searchUser.getCompany().getCompanyCode());
			companys.put("text", searchUser.getCompany().getCoName());
			companys.put("etc", searchUser.getCompany().getCoId());
			companyList.add(companys);
					
			rtn.put("result", companyList);
			return rtn;
		}	
	}
	
	//my information
	@GetMapping("/myInfo")
	public ModelAndView showMyInfo(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();

		ModelAndView view = new ModelAndView();

		UserVO userDetail = userService.getOneUser(user.getUsername());
		
		view.setViewName("user/view");
		view.addObject("myInfo", userDetail);
		return view;
	}
	
	//modifyUser
	@PostMapping("/modify")
	@ResponseBody
	public String actionEditUser(@ModelAttribute("modifyForm") 
			@RequestBody @Valid UserVO modifyUser, HttpSession session){
		UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
		UserVO modifyUserOld = userService.getOneUser(modifyUser.getUsername());
		//권한 확인 : 관리자거나 본인이거나
		if( !sessionUser.getUserId().equals(modifyUserOld.getUserId())) {
			if(sessionUser.getRole() != 3 && sessionUser.getRole() != 5 && sessionUser.getRole() != 6) {
				return "/user/list";
			}
		}
		boolean isModify = false;
		//관리자가 변경했을 때
		if(sessionUser.getRole() == 5 || sessionUser.getRole() == 6) {
			//회사 변경
			if( modifyUserOld.getCompany().getCoId() != modifyUser.getCompany().getCoId()) {
				modifyUserOld.getCompany().setCoId(modifyUser.getCompany().getCoId());
				isModify = true;
			}
			//사용자 권한변경
			if( modifyUserOld.getRole() != modifyUser.getRole() ) {
				log.debug("여기니??");
				modifyUserOld.setRole(modifyUser.getRole());
				isModify = true;
			}
		}
		
		if(sessionUser.getRole() == 3 || sessionUser.getRole() == 5 || sessionUser.getRole() == 6) {
			//사용자 상태(재직,퇴직,사용중지) 변경
			if( modifyUserOld.getStatus() != modifyUser.getStatus() ) {
				modifyUserOld.setStatus(modifyUser.getStatus());
				isModify = true;
			}
		}
		// 사용자명
		if( modifyUserOld.getuName() != modifyUser.getuName() ) {
			modifyUserOld.setuName(modifyUser.getuName());
			isModify = true;
		}
		// 사용자명(En)
		if( modifyUserOld.getUserNameEN() != modifyUser.getUserNameEN() ) {
			modifyUserOld.setUserNameEN(modifyUser.getUserNameEN());
			isModify = true;
		}
		//secUser에 user등록처리		
        SecUser secUser = new SecUser();
        
        if(modifyUser.getPassword() !=null && modifyUser.getPasswordConfirm() !=null) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        secUser.setPassword(passwordEncoder.encode(modifyUser.getPassword()));
	        secUser.setUsername(modifyUser.getUsername());
	        secUser.setId(sessionUser.getUserId());
	        authService.changePassword(secUser);       
        }
        
		// 이름변경
		if( modifyUserOld.getuName() != modifyUser.getuName() ) {
			modifyUserOld.setuName(modifyUser.getuName());
			isModify = true;
		}
		
		boolean isModifyUserModule = false;
		
		if(modifyUserOld.getRole()>3) {
			modifyUser.setIsCustomer(0);
		}else {
			modifyUser.setIsCustomer(1);
		}
		
		// 직책변경
		if( modifyUserOld.getuTitle() != modifyUser.getuTitle() ) {
			modifyUserOld.setuTitle(modifyUser.getuTitle());
			isModify = true;
		}
		// 담당업무
		if( modifyUserOld.getBusinessDesc() != modifyUser.getBusinessDesc() ) {
			modifyUserOld.setBusinessDesc(modifyUser.getBusinessDesc());
			isModify = true;
		}
		// 전화번호
		if( modifyUserOld.getTel() != modifyUser.getTel() ) {
			modifyUserOld.setTel(modifyUser.getTel());
			isModify = true;
		}
		// fax번호
		if( modifyUserOld.getFax() != modifyUser.getFax() ) {
			modifyUserOld.setFax(modifyUser.getFax());
			isModify = true;
		}
		// 휴대전화
		if( modifyUserOld.getMobile() != modifyUser.getMobile() ) {
			modifyUserOld.setMobile(modifyUser.getMobile());
			isModify = true;
		}
		// 이메일변경
		if( modifyUserOld.getEmail() != modifyUser.getEmail() ) {
			modifyUserOld.setEmail(modifyUser.getEmail());
			isModify = true;
		}
		// 부서변경
		if( modifyUserOld.getDept() != modifyUser.getDept() ) {
			modifyUserOld.setDept(modifyUser.getDept());
			isModify = true;
		}
		
		if ( isModify ) {
			if ( userService.modifyUser(modifyUserOld) ) {
				if( sessionUser.getUserId() ==  modifyUserOld.getUserId()) {
					return "/user/myInfo";
				}
				return "/user/view/"+ modifyUserOld.getUserId(); 
			}			
		}
		return "/user/list";
	}
	
	//사용자 조회 화면
	//TODO ajax로 변경해야됨~~
	@GetMapping("/view/{id}")
	public ModelAndView showUserDetail(@PathVariable int id, HttpSession session){
		UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
		ModelAndView view = new ModelAndView();
		UserVO userDetail = userService.getOneUser(id);
		
		
		List<CompanyVO> companyList = null;
		boolean roleCheck = false;
		if ( sessionUser.getCompany().getRoleId() == 2 ) {
			//customer : 자기 회사만
			if(sessionUser.getCompany().getCoId() != userDetail.getCompany().getCoId()) {
				return new ModelAndView("redirect:/user/list");
			}
		}
		else if(sessionUser.getCompany().getRoleId() == 3){
			//customer : 자기 회사만
			if(sessionUser.getCompany().getCoId() != userDetail.getCompany().getCoId()) {
				return new ModelAndView("redirect:/user/list");
			}
		}
		
		view.setViewName("user/view");
		view.addObject("myInfo", userDetail);		
		view.addObject("sessionUser", sessionUser);		
		return view;	
	}
    
	//create user view
	@GetMapping("/create")
	public String showCreateUser(HttpSession session){
		System.out.println(session);
		UserVO sessionInfo = (UserVO) session.getAttribute(User.USER);
		
		
		System.out.println(sessionInfo.getRole() );

		//session 권한확인 후 create화면으로 이동 아니면 main
		if( sessionInfo.getRole() == 6) {
			return "user/create";
		}

		return "/main";		
	}
	
	//create user
    @PostMapping("/create")
    @ResponseBody
	public String actionCreateUser(@ModelAttribute("createForm") @RequestBody @Valid UserVO newUser, HttpSession session){
		UserVO sessionInfo = (UserVO) session.getAttribute(User.USER);
		log.debug("생성하러 왓습니다~~");
		log.debug("확인확인확인 : "+newUser.getCompany().getCoId());
		//관리자 권한 체크
		
		
		if( sessionInfo.getRole() != 6) {
			log.debug("1");
			return "redirect:/user/list";
		}
	
		if( newUser.getCompany().getCoId() == 0 ) {
			log.debug("2");
			//회사 정보가 없으면 튕겨내기
			return "redirect:/user/list";
		}		
		//log.debug("secUser : " + secUser.getPassword());
		
		log.debug("3");
		
		//null체크 - 요애들은 필수입력
		if( newUser.getCompany() == null ) {
			log.debug("4");
			return "redirect:/user/list";
		}
		if( newUser.getPassword() == null ) {
			log.debug("4");
			return "redirect:/user/list";
		}
		if( newUser.getUsername() == null ) {
			log.debug("5");
			return "redirect:/user/list";
		}
		if( newUser.getEmail() == null ) {
			log.debug("6");
			return "redirect:/user/list";
		}		
		if( newUser.getTel() == null ) {
			log.debug("7");
			return "redirect:/user/list";
		}		
		if( newUser.getMobile() == null ) {
			log.debug("8");
			return "redirect:/user/list";
		}
		
		//secUser에 user등록처리		
        SecUser secUser = new SecUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        secUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        secUser.setUsername(newUser.getUsername());       
        secUser = authService.createSecUser(secUser);       
        authService.createSecUserSecRole(secUser);
		
		UserVO userDetail = userService.createUser(newUser);//저장후 id를 받음
		
		return "/user/list";
	}
    
    @RequestMapping("/checkID")
    @ResponseBody
    public int checkUserID(@RequestBody String id) {
    	int cnt = 0;
    	SecUser user = new SecUser();
    	user.setUsername("");  // nullpointException
    	user = authService.getSecUserInfoByUserName(id); 
    	if(user == null) {
    	} else {
    		cnt = 1;
    	}
    	
		return cnt;
    	
    }
    
    //user삭제
    @PostMapping("/remove")
	public String actionRemoveUser(@PathVariable int id, HttpSession session){
		UserVO sessionInfo = (UserVO) session.getAttribute(User.USER);

		log.debug("삭제하러 왓습니다~~");
		
		//관리자 권한 체크
		if( sessionInfo.getRole() != 6) {
			log.debug("1");
			return "redirect:/user/list";
		}
    	
    	/**
    	 * 유저를 삭제하면
		tb_user의 유저 삭제
		tb_usr_module에 모듈삭제
		sec_user에서 삭제?
		근데 유저를 삭제 안하는것 같은데.. 그냥 사용중지?
		우선 만들어는 둠
    	 */
		
		if( userService.clearUser(id) ) {
			return "redirect:/user/list";
		}
		
    	
    	return "redirect:/user/list";
    }
}