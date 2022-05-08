package kr.co.biztechpartners.serveSocket.common.security.service;

import kr.co.biztechpartners.serveSocket.common.security.vo.SecRole;
import kr.co.biztechpartners.serveSocket.common.security.vo.SecUser;
import kr.co.biztechpartners.serveSocket.common.security.vo.SecurityUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AuthUserDetailService implements UserDetailsService {
	
    @Autowired
    AuthService authService;

    private static final Logger log = LoggerFactory.getLogger(AuthUserDetailService.class);
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecUser user = authService.getSecUserInfoByUserName(username);

        if (user == null || user.getUsername() == null) {
        	// 아이디 미존재
        	throw new NullPointerException("아이디 또는 비밀번호가 일치하지 않습니다.");
        } else if (user.isPassword_expired()) {
        	// 패스워드 만료
        	throw new BadCredentialsException("비밀번호가 유효하지 않습니다.");
        }
        
        String sLoginDate = user.getLast_login_date();
        if (sLoginDate != null && !sLoginDate.equals("")) {
        	SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        	Date today = new Date();
        	Date loginDate = null;
        	try {
				loginDate = sm.parse(sLoginDate);
			} catch (ParseException e) {
				loginDate = new Date();
			}
        	Calendar cal = Calendar.getInstance();
    		cal.setTime(loginDate);
    		cal.add(Calendar.DATE, 90);
    		loginDate = cal.getTime();
    		
        	if (today.compareTo(loginDate) > 0) {
        		// 마지막 로그인 시점이 90이 이전인 경우 비밀번호 변경
        		throw new BadCredentialsException("마지막 로그인으로 부터 90일이 경과하였습니다. 비밀번호 변경 후 다시 로그인해 주세요.");
        	}
        }

        List<SecRole> roles = authService.getSecRoleListByUserName(username);
        
        return Optional.ofNullable(user)
                .map(secUser -> new SecurityUser(user.getUsername(),user.getPassword(),roles)).orElse(null);
    }
}
