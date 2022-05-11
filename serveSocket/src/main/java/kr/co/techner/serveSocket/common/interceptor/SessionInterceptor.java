package kr.co.techner.serveSocket.common.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.techner.serveSocket.common.security.vo.SecurityUser;
import kr.co.techner.serveSocket.user.constant.User;
import kr.co.techner.serveSocket.user.service.UserService;
import kr.co.techner.serveSocket.user.vo.UserVO;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(SessionInterceptor.class);
	
	@Autowired
    private UserService userService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		UserVO myInfo = (UserVO) session.getAttribute(User.USER);
		if(myInfo==null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null) {
				if(!"anonymousUser".equals(auth.getPrincipal())) {
					
					SecurityUser user = (SecurityUser) auth.getPrincipal();
					myInfo = userService.getOneUser(user.getUsername());
					session.setAttribute(User.USER, myInfo);
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	

}
