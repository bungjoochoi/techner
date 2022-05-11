package kr.co.techner.serveSocket.common.error.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.co.techner.serveSocket.common.security.service.AuthService;
import kr.co.techner.serveSocket.common.security.vo.SecUser;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Autowired
    AuthService authService;
	
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		//session.removeAttribute("PASSWORD_RESET");
		
		redirectStratgy.sendRedirect(request, response, "/login/form");
		//request.getRequestDispatcher("/login/form").forward(request, response);
	}
}
