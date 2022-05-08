package kr.co.biztechpartners.serveSocket.common.security.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.biztechpartners.serveSocket.common.security.service.AuthService;
import kr.co.biztechpartners.serveSocket.common.security.vo.SecRole;
import kr.co.biztechpartners.serveSocket.common.security.vo.SecUser;
import kr.co.biztechpartners.serveSocket.common.security.vo.SecurityUser;
import kr.co.biztechpartners.serveSocket.common.util.service.MessageSendService;
import kr.co.biztechpartners.serveSocket.user.constant.User;
import kr.co.biztechpartners.serveSocket.user.service.UserService;
import kr.co.biztechpartners.serveSocket.user.vo.UserVO;


@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
	MessageSendService messageSendService;

    @GetMapping("/accesserror")
    public String accessLimit() {
        return "error/access";
    }
    
    @GetMapping("/login/form")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        //TODO
        return "login/login";
    }
    @PostMapping("/logout")
    public String logoutPost(Model model) {
        //TODO
        return "index";
    }

    //회원가입 임시 소스
    @PostMapping("/authuser")
    public String create(SecUser secUser) {
        SecRole secRole = new SecRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        secUser.setPassword(passwordEncoder.encode(secUser.getPassword()));
        secRole.setAuthority("USER");
//        secUser.setRoles(Arrays.asList(secRole));
       // authService.updateSecUser(secUser);
       // authService.insertSecUserRole(secUser);

        return "redirect:/";
    }

  //password 찾기
    @PostMapping("/login/modifyPassword")
    public String modifyPassword(@ModelAttribute("passForm") @Valid SecUser secUser ) {
    	//@RequestParam String userId 
    	//TODO 패스워드 변경 로직 심기
    	/**
    	 * 1. 메일 id를 확인하고
    	 * 2. sms 본인인증을 한다.
    	 * 3. password를 db에 저장함
    	 * 4. login page로 변경
    	 */
    	
    	log.debug("controller들어왓다 확인");
    	
    	//1. 메일 id로 user가 있는지 확인하고
    	SecUser oldpass = authService.getSecUserInfoByUserName(secUser.getUsername());
    	log.debug("1111확인1111: " + oldpass);
    	if ( oldpass == null ) {
    		return "auth/modifyPassword";   
    	}
    	//user가 있으면 secUser에 데이터를 부어준당
    	oldpass.setPassword(secUser.getPassword());
    	secUser = oldpass;    	
    	//log.debug("user정보확인 : " + secUser.getId());
    	
    	//2. sms / email 본인인증을 한다.
    	
    	
    	
    	
    	//3. password를 db에 저장함
    	//user password 암호화, 권한주기
    	SecRole secRole = new SecRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        secUser.setPassword(passwordEncoder.encode(secUser.getPassword()));
        secRole.setAuthority("USER");        
        
        //4. update에 성공하면 login page로 이동
        boolean isUpdatePass = authService.updateSecUser(secUser);
        if (isUpdatePass) {
        	return "redirect:/";
        }
        return "auth/modifyPassword";  
        
    }
    
    @GetMapping("/main")
    public ModelAndView main(HttpServletRequest request){
        ModelAndView main = new ModelAndView("/main");
        HttpSession session = request.getSession();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        
        UserVO myInfo = userService.getOneUser(user.getUsername());
        session.setAttribute(User.USER, myInfo);
        
        main.addObject("myInfo", myInfo);
        
        return main;
    }
    
    @GetMapping("/agree")
    public String viewAgree(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       
        return "common/agree";  
    }
    
    @GetMapping("/privacy")
    public String viewPrivacy(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       
        return "common/privacy";  
    }
    
    @GetMapping("/overview")
    public String viewOverview(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       
        return "common/overview";  
    }
    
    @GetMapping("/auth/changePopup")
    public ModelAndView changePopup(HttpSession session){
    	ModelAndView view = new ModelAndView("/auth/changePassword");
		
    	UserVO myInfo = (UserVO) session.getAttribute(User.USER);
    	view.addObject("myInfo", myInfo);
    	
		return view;
    }
    
    @GetMapping("/auth/changeExamPopup")
    public ModelAndView changeExamPopup(HttpSession session){
    	ModelAndView view = new ModelAndView("/auth/changeExamPassword");
    	
    	UserVO myInfo = (UserVO) session.getAttribute(User.USER);
    	view.addObject("myInfo", myInfo);
    	
    	return view;
    }
    
    @PostMapping("/auth/changePassword")
    @ResponseBody
    public void changePassword(@RequestBody SecUser secUser, HttpSession session) {
    	
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        secUser.setPassword(passwordEncoder.encode(secUser.getPassword()));
        UserVO myInfo = (UserVO) session.getAttribute(User.USER);
        String username = myInfo.getUsername();
        secUser.setUsername(username);
    	
        authService.changePassword(secUser);
    }
    
    @PostMapping("/auth/changeExamPassword")
    @ResponseBody
    public void changeExamPassword(@RequestBody HashMap<String, String> map) {
    	
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String encodePassword = passwordEncoder.encode(map.get("password"));            	
        if(map.remove("password", map.get("password"))) {
        	map.put("password", encodePassword);
        }
        
        authService.changeExamPassword(map); 
    }
    
    //임시비밀번호 메일 발송 부분 없음
    @PostMapping("/auth/sendPassword")
    @ResponseBody
    public void sendPassword(@RequestBody HashMap<String, String> map) {    	
    	// 비밀번호 랜덤생성
    	char pwCollection[] = new char[] {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    	String newpw = "";
    	for (int i = 0; i < 8; i++) {
    		int randomNum = (int)(Math.random()*(pwCollection.length));  
    		newpw += pwCollection[randomNum];  
    	}
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	//String newpw = map.get("password");
    	String encodePassword = passwordEncoder.encode(newpw);  
       	map.put("password", encodePassword);
        
    	authService.changeExamPassword(map);
    	
    	
    	String subject = "[Biztech-ServeSocket] 비밀번호 변경안내";
		  //msg에 들어갈 꺼
    	HashMap<String, Object> vmmap = new HashMap<String, Object>();
    	UserVO user = userService.getOneUser(map.get("userId"));
    	
    	vmmap.put("myInfo",user);
    	map.put("newpw", newpw);
    	vmmap.put("map",map);
    	
    	String msg = messageSendService.geContentFromTemplate("password_init.vm", vmmap);
    	messageSendService.sendMail(msg, map.get("userId") , subject); 
    	
    }
    
    @PostMapping("/auth/existUser")
    @ResponseBody
    public UserVO existUser(@RequestBody HashMap<String, String> map) {    	
    	
    	return authService.existUser(map); 		
    	
    }
    
    @GetMapping("/")
    public String reMain(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        return "redirect:/main";
    }


}
