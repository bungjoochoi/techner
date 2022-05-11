package kr.co.techner.serveSocket.common.security.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.co.techner.serveSocket.common.security.service.AuthService;
import kr.co.techner.serveSocket.common.security.vo.SecUser;


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
    AuthService authService;
	
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		
		redirectStratgy.sendRedirect(request, response, "/main");
	}
}
