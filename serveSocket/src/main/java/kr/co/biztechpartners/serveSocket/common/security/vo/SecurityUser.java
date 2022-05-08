package kr.co.biztechpartners.serveSocket.common.security.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final Long serialVersionUID = 1L;


    public SecurityUser(String username, String password,List<SecRole> roles)  {
        super(username,password,makeGrantedAuthority(roles));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<SecRole> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX+role.getAuthority())).collect(Collectors.toList());

    }
}
