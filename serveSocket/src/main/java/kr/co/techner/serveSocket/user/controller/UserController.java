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
	
	//????????? ?????? main?????????
	@GetMapping("/list")
	public ModelAndView showUserList(HttpSession session){
		//user????????? session?????? ????????? ????????? ?????? ??????
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		
		ModelAndView view = new ModelAndView();		
		
		// user??? ?????? ???????????? ?????? ????????? user????????? ????????? - ?????????????????????
		List<UserVO> userList = userService.getMyCompanyCustomerList(searchUser.getCompany().getCoId());
		
		view.setViewName("user/list");
		view.addObject("userList", userList);
				
		return view;
	}
	
	//????????? ?????? - ?????? ?????? ??? ajax
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
	
	//?????????  ?????????????????? ??????
	@RequestMapping(value="/listAjax", method = RequestMethod.GET)
	public ModelAndView actionSearchUser(HttpSession session, @ModelAttribute("searchForm")  @Valid UserSearchVO userSearch ){
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		if ( userSearch.getSearchType() == 0 ) {
			//????????????
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
		 * ?????? ?????? ??? ?????? ??????(??????)?????? ??????
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
			map.put("text", "---??????---" );
			companyList.add(map);
		}
				
		ModelAndView view = new ModelAndView();

		view.setViewName("user/list");
		view.addObject("userList", searchUserList);
		view.addObject("companyList", companyList);
		view.addObject("userSearch", userSearch);
				
		return view;	
	}

	//????????? ?????????????????? ??????selector??? ???????????? ajax
	//outsourcingd??? ?????? ????????? ???????????? ??? ??? ??????
	//customer?????? ??????????????? ??? ??? ??????
	@RequestMapping(value="/companySelectorListAjax")
	@ResponseBody
	public HashMap<String, Object> companyListAjax(HttpSession session, String q){
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		log.debug("------------==============????????????????=====================");
		List<CompanyVO> readCoList = null;
		List<HashMap<String, Object>> companyList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> companys = null;
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		log.debug("??????????????? : "+q);
		if( searchUser.getCompany().getRoleId() == 1) {
			//biztech ?????? ????????? ??? ?????????
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
			//???????????? customer??? ?????? ????????? ???????????? ??? ??? ??????
			companys = new HashMap<String, Object>();
			companys.put("id", searchUser.getCompany().getCoId());
			companys.put("text", searchUser.getCompany().getCoName());
			companyList.add(companys);
					
			rtn.put("result", companyList);
			return rtn;
		}	
	}	
	
	
	//????????? ?????????????????? ??????selector??? ???????????? ajax
	//outsourcingd??? ?????? ????????? ???????????? ??? ??? ??????
	//customer?????? ??????????????? ??? ??? ??????
	@RequestMapping(value="/coSelectorListAjax")
	@ResponseBody
	public HashMap<String, Object> coListAjax(HttpSession session, String q){
		UserVO searchUser = (UserVO) session.getAttribute(User.USER);
		log.debug("------------==============????????????????=====================");
		List<CompanyVO> readCoList = null;
		List<HashMap<String, Object>> companyList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> companys = null;
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		log.debug("??????????????? : "+q);
		if( searchUser.getCompany().getRoleId() == 1) {
			//biztech ?????? ????????? ??? ?????????
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
			//???????????? customer??? ?????? ????????? ???????????? ??? ??? ??????
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
		//?????? ?????? : ??????????????? ???????????????
		if( !sessionUser.getUserId().equals(modifyUserOld.getUserId())) {
			if(sessionUser.getRole() != 3 && sessionUser.getRole() != 5 && sessionUser.getRole() != 6) {
				return "/user/list";
			}
		}
		boolean isModify = false;
		//???????????? ???????????? ???
		if(sessionUser.getRole() == 5 || sessionUser.getRole() == 6) {
			//?????? ??????
			if( modifyUserOld.getCompany().getCoId() != modifyUser.getCompany().getCoId()) {
				modifyUserOld.getCompany().setCoId(modifyUser.getCompany().getCoId());
				isModify = true;
			}
			//????????? ????????????
			if( modifyUserOld.getRole() != modifyUser.getRole() ) {
				log.debug("???????????");
				modifyUserOld.setRole(modifyUser.getRole());
				isModify = true;
			}
		}
		
		if(sessionUser.getRole() == 3 || sessionUser.getRole() == 5 || sessionUser.getRole() == 6) {
			//????????? ??????(??????,??????,????????????) ??????
			if( modifyUserOld.getStatus() != modifyUser.getStatus() ) {
				modifyUserOld.setStatus(modifyUser.getStatus());
				isModify = true;
			}
		}
		// ????????????
		if( modifyUserOld.getuName() != modifyUser.getuName() ) {
			modifyUserOld.setuName(modifyUser.getuName());
			isModify = true;
		}
		// ????????????(En)
		if( modifyUserOld.getUserNameEN() != modifyUser.getUserNameEN() ) {
			modifyUserOld.setUserNameEN(modifyUser.getUserNameEN());
			isModify = true;
		}
		//secUser??? user????????????		
        SecUser secUser = new SecUser();
        
        if(modifyUser.getPassword() !=null && modifyUser.getPasswordConfirm() !=null) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        secUser.setPassword(passwordEncoder.encode(modifyUser.getPassword()));
	        secUser.setUsername(modifyUser.getUsername());
	        secUser.setId(sessionUser.getUserId());
	        authService.changePassword(secUser);       
        }
        
		// ????????????
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
		
		// ????????????
		if( modifyUserOld.getuTitle() != modifyUser.getuTitle() ) {
			modifyUserOld.setuTitle(modifyUser.getuTitle());
			isModify = true;
		}
		// ????????????
		if( modifyUserOld.getBusinessDesc() != modifyUser.getBusinessDesc() ) {
			modifyUserOld.setBusinessDesc(modifyUser.getBusinessDesc());
			isModify = true;
		}
		// ????????????
		if( modifyUserOld.getTel() != modifyUser.getTel() ) {
			modifyUserOld.setTel(modifyUser.getTel());
			isModify = true;
		}
		// fax??????
		if( modifyUserOld.getFax() != modifyUser.getFax() ) {
			modifyUserOld.setFax(modifyUser.getFax());
			isModify = true;
		}
		// ????????????
		if( modifyUserOld.getMobile() != modifyUser.getMobile() ) {
			modifyUserOld.setMobile(modifyUser.getMobile());
			isModify = true;
		}
		// ???????????????
		if( modifyUserOld.getEmail() != modifyUser.getEmail() ) {
			modifyUserOld.setEmail(modifyUser.getEmail());
			isModify = true;
		}
		// ????????????
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
	
	//????????? ?????? ??????
	//TODO ajax??? ???????????????~~
	@GetMapping("/view/{id}")
	public ModelAndView showUserDetail(@PathVariable int id, HttpSession session){
		UserVO sessionUser = (UserVO) session.getAttribute(User.USER);
		ModelAndView view = new ModelAndView();
		UserVO userDetail = userService.getOneUser(id);
		
		
		List<CompanyVO> companyList = null;
		boolean roleCheck = false;
		if ( sessionUser.getCompany().getRoleId() == 2 ) {
			//customer : ?????? ?????????
			if(sessionUser.getCompany().getCoId() != userDetail.getCompany().getCoId()) {
				return new ModelAndView("redirect:/user/list");
			}
		}
		else if(sessionUser.getCompany().getRoleId() == 3){
			//customer : ?????? ?????????
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

		//session ???????????? ??? create???????????? ?????? ????????? main
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
		log.debug("???????????? ????????????~~");
		log.debug("?????????????????? : "+newUser.getCompany().getCoId());
		//????????? ?????? ??????
		
		
		if( sessionInfo.getRole() != 6) {
			log.debug("1");
			return "redirect:/user/list";
		}
	
		if( newUser.getCompany().getCoId() == 0 ) {
			log.debug("2");
			//?????? ????????? ????????? ????????????
			return "redirect:/user/list";
		}		
		//log.debug("secUser : " + secUser.getPassword());
		
		log.debug("3");
		
		//null?????? - ???????????? ????????????
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
		
		//secUser??? user????????????		
        SecUser secUser = new SecUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        secUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        secUser.setUsername(newUser.getUsername());       
        secUser = authService.createSecUser(secUser);       
        authService.createSecUserSecRole(secUser);
		
		UserVO userDetail = userService.createUser(newUser);//????????? id??? ??????
		
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
    
    //user??????
    @PostMapping("/remove")
	public String actionRemoveUser(@PathVariable int id, HttpSession session){
		UserVO sessionInfo = (UserVO) session.getAttribute(User.USER);

		log.debug("???????????? ????????????~~");
		
		//????????? ?????? ??????
		if( sessionInfo.getRole() != 6) {
			log.debug("1");
			return "redirect:/user/list";
		}
    	
    	/**
    	 * ????????? ????????????
		tb_user??? ?????? ??????
		tb_usr_module??? ????????????
		sec_user?????? ???????
		?????? ????????? ?????? ???????????? ?????????.. ?????? ?????????????
		?????? ???????????? ???
    	 */
		
		if( userService.clearUser(id) ) {
			return "redirect:/user/list";
		}
		
    	
    	return "redirect:/user/list";
    }
}