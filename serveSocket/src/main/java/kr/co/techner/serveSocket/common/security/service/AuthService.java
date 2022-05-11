package kr.co.techner.serveSocket.common.security.service;

import kr.co.techner.serveSocket.common.security.vo.SecRole;
import kr.co.techner.serveSocket.common.security.vo.SecUser;
import kr.co.techner.serveSocket.user.vo.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	
	
    @Autowired
    private kr.co.techner.serveSocket.common.security.repository.AuthMapper AuthMapper;

    public boolean updateSecUser(SecUser secUser) {
    	return AuthMapper.updateSecUser(secUser) > 0;
    }

    public SecUser createSecUser(SecUser secUser) {
    	AuthMapper.insertSecUser(secUser);
    	log.debug("sec Id check : "+secUser.getId());
    	return secUser;
    }
    
    public boolean createSecUserSecRole(SecUser secUser) {
    	return AuthMapper.insertSecUserSecRole(secUser)>0;
    }

    public SecUser getSecUserInfoByUserName(String username) {
        return AuthMapper.getSecUserInfoByUserName(username);
    }
    public List<SecRole> getSecRoleListByUserName(String username) {
        return AuthMapper.getSecRoleListByUserName(username);
    }
    
    public int changePassword(SecUser secUser) {
    	return AuthMapper.changePassword(secUser);
    }
    
    public int changeExamPassword(HashMap<String, String> map) {
    	return AuthMapper.changeExamPassword(map);
    }
    
    public UserVO existUser(HashMap<String, String> map) {
		return AuthMapper.existUser(map);
	}
    
    public int updateAccountCondition(SecUser secUser) {
    	return AuthMapper.updateAccountCondition(secUser);
    }
    
    public SecUser accountCondition(SecUser secUser) {
    	return AuthMapper.accountCondition(secUser);
    }
}
