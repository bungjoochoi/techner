package kr.co.biztechpartners.serveSocket.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.co.biztechpartners.serveSocket.common.error.handler.CustomAuthenticationFailureHandler;
import kr.co.biztechpartners.serveSocket.common.security.controller.CustomAuthenticationSuccessHandler;
import kr.co.biztechpartners.serveSocket.common.security.service.AuthUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthUserDetailService authUserDetailService;


	//	 로그인 URL , 권한분리, logout URL
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//		.antMatchers("/").hasAnyRole("ANONYMOUS","USER")
				.antMatchers("/login/form/*","/files/*").hasAnyRole("ANONYMOUS","USER")
		//		.antMatchers("/main").hasAnyRole("ADMIN","USER")
				.antMatchers("/login/modifyPassword", "/login/modifyPassword/*").hasAnyRole("ANONYMOUS","USER")
				.antMatchers("/auth/existUser", "/auth/existUser/*").hasAnyRole("ANONYMOUS","USER")
				.antMatchers("/auth/sendPassword", "/auth/sendPassword/*").hasAnyRole("ANONYMOUS","USER")
		//		.antMatchers("/admin/*").hasRole("ADMIN")

				.anyRequest().fullyAuthenticated()

				.and().formLogin()
					.loginPage("/login/form")
					.loginProcessingUrl("/authenticate")
					.successHandler(this.authenticationSuccessHandler())
					//.defaultSuccessUrl("/main",true)
					.failureHandler(this.authenticationFailureHandler())
					//.failureUrl("/login/form?error")
					.usernameParameter("userid")
					.passwordParameter("userPassword")
					.permitAll()
					
				.and().logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login/form?logout")
					.deleteCookies("JSESSIONID","remember-me")
					
				.and().csrf().disable()
					.headers().frameOptions().disable()
				
				.and().rememberMe()
					.key("myUniqueKey")		
					.rememberMeCookieName("websparrow-login-remember-me")
	                .tokenValiditySeconds(86400*3)
                ;
	}

	@Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();	// khj
    }
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();	// khj
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider);
		auth.userDetailsService(authUserDetailService).passwordEncoder(passwordEncoder());
	}


	// 로그인 상관없이
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**","/css/**","/images/**","/fonts/**","/files/**");
//		web.ignoring().antMatchers("/js/**");
	}
}
