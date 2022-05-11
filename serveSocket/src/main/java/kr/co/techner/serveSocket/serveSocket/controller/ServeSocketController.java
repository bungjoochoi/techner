package kr.co.techner.serveSocket.serveSocket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.techner.serveSocket.serveSocket.service.ServeSocketService;
import kr.co.techner.serveSocket.serveSocket.vo.ServeSocket;
import kr.co.techner.serveSocket.user.constant.User;
import kr.co.techner.serveSocket.user.service.UserService;
import kr.co.techner.serveSocket.user.vo.UserVO;

@Controller
@RequestMapping(value = "/serveSocket")
public class ServeSocketController {

    private static final Logger log = LoggerFactory.getLogger(ServeSocketController.class);

    @Autowired
    ServeSocketService serveSocketService;
    
    @Autowired
	UserService userService;

    @GetMapping("/list")
    public ModelAndView main(HttpSession session){
        ModelAndView list = new ModelAndView("serveSocket/list");
    	
        UserVO myInfo = (UserVO) session.getAttribute(User.USER);

    	list.addObject("myInfo", myInfo);
        
        return list;
    }

    @GetMapping(value="/serveSocketListAjax")
    @ResponseBody
    public List<ServeSocket> serveSocketListAjax (HttpSession session){
    	
    	ServeSocket vo = new ServeSocket();
    	List<ServeSocket> serveSocketListAjax = serveSocketService.serveSocketListAjax(vo);

    	return serveSocketListAjax;
    	
    }
}
