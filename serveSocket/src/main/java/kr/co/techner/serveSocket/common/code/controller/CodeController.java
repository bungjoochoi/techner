package kr.co.techner.serveSocket.common.code.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.techner.serveSocket.common.code.service.CodeService;
import kr.co.techner.serveSocket.company.service.CompanyService;
import kr.co.techner.serveSocket.company.vo.CompanyVO;
import kr.co.techner.serveSocket.user.constant.User;
import kr.co.techner.serveSocket.user.service.UserService;
import kr.co.techner.serveSocket.user.vo.UserSearchVO;
import kr.co.techner.serveSocket.user.vo.UserVO;

@Controller
@RequestMapping(value = "/common/code")
public class CodeController {

    private static final Logger log = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    CodeService codeService;
    
    @Autowired
    CompanyService companyService;
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value="/companyServiceOnListAjax")
   	@ResponseBody
    public HashMap<String, Object> companyServiceOnListAjax (HttpSession session){
    	log.debug("#####companyServiceOnListAjax ::::::::");
    	List<CompanyVO> searchCompanyList = companyService.getSearchCompanyList(null);
    	log.debug("######" + searchCompanyList);
    	List<HashMap<String, Object>> companyList = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> companys = null;
    	HashMap<String, Object> rtn = new HashMap<String, Object>();
    	for(CompanyVO company : searchCompanyList) {
			companys = new HashMap<String, Object>();
			companys.put("id", company.getCoId());
			companys.put("text", company.getCoName());
			companyList.add(companys);
		}		
    	rtn.put("result", companyList);
		return rtn;
    	
    }

    //?????? ?????? ???????????? ajax
    @RequestMapping(value="/companyListAjax")
	@ResponseBody
	public HashMap<String, Object> companyListAjax (HttpSession session, String q, String type){
    	UserVO searchUser = (UserVO) session.getAttribute(User.USER);
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
				if(type == null || (type.equals("isServiced") && company.getIsServiced() == 1)) {
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
    
    //?????? ?????? ???????????? ajax
    @RequestMapping(value="/userListAjax")
    @ResponseBody
    public HashMap<String, Object> userListAjax (@ModelAttribute("user") UserSearchVO userSearchVO, HttpSession session){
    	UserVO searchUser = (UserVO) session.getAttribute(User.USER);
    	
    	log.debug("userListAjax : "+userSearchVO);
    	
    	log.debug("searchUser : "+searchUser);
    	

    	HashMap<String, Object> rtn = new HashMap<String,Object>();
    	
    	if(searchUser.getRole()>=4 && userSearchVO.getSearchCompanyId() == null ) {
    		List<HashMap<String, String>> listAjax = codeService.userAdminListAjax(userSearchVO);
        	rtn.put("result", listAjax);

    	} else {
        	if( userSearchVO.getSearchCompanyId() == null ) {
        		userSearchVO.setSearchCompanyId(Integer.toString(searchUser.getCompany().getCoId()));
        	}
    		List<HashMap<String, String>> listAjax = codeService.userListAjax(userSearchVO);
        	rtn.put("result", listAjax);
    	}
    	return rtn;
    }
    
  //???????????? ?????? ???????????? ajax
    @RequestMapping(value="/companyRoleSelect")
	@ResponseBody
	public HashMap<String, Object> companyRoleListAjax(String id){    	
		List<HashMap<String, String>> listAjax = codeService.getCompanyRoleList(id);
		HashMap<String, Object> rtn = new HashMap<String,Object>();
		rtn.put("result", listAjax);
		log.debug("????????? : "+rtn);
		return rtn;
	}
	
    //?????? ?????? ?????? ???????????? ajax
    @RequestMapping(value="/userRoleSelect")
	@ResponseBody
	public HashMap<String, Object> userRoleListAjax (@RequestParam(required = false) Integer id){
		//vo.setAdminId((String) session.getAttribute("sessionId"));
		//vo.setAdminGbn((String)session.getAttribute("adminGbn"));
		List<HashMap<String, String>> listAjax = codeService.getUserRole(id);
		HashMap<String, Object> rtn = new HashMap<String,Object>();
		rtn.put("result", listAjax);
		log.debug("??? ?????? : "+rtn);
		return rtn;
	}
    
    //????????? ?????? ?????? ???????????? ajax
    @RequestMapping(value="/userStatusSelect")
	@ResponseBody
	public HashMap<String, Object> userStatusListAjax (@RequestParam(required = false) Integer id){
		List<HashMap<String, String>> listAjax = userService.getUserStatus();
		HashMap<String, Object> rtn = new HashMap<String,Object>();
		rtn.put("result", listAjax);
		return rtn;
	}
    
    //??????_MD ?????? ???????????? ajax
    @RequestMapping(value="/commonCodeSelect")
    @ResponseBody
    public Object commonCodeListAjax (@RequestParam(required = false) String classCode){
    	System.out.println("commonCodeListAjax : " + classCode);
    	List<HashMap<String, String>> listAjax = codeService.getCommonCode(classCode);
    	return listAjax;
    }
    
    //??????_MD ?????? ???????????? ajax
    @RequestMapping(value="/classCodeSelect")
    @ResponseBody
    public Object classCodeListAjax (){
    	List<HashMap<String, String>> listAjax = codeService.getClassCode();
    	return listAjax;
    }
    
    //??????_MT ?????? ???????????? ajax
    @RequestMapping(value="/senseMacidSelect")
    @ResponseBody
    public Object senseMacidListAjax (){
    	List<HashMap<String, String>> listAjax = codeService.getSenseMacid();
    	return listAjax;
    }
}
