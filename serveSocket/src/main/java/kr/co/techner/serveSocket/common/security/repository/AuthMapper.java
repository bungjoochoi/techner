package kr.co.techner.serveSocket.common.security.repository;

import kr.co.techner.serveSocket.common.security.vo.SecRole;
import kr.co.techner.serveSocket.common.security.vo.SecUser;
import kr.co.techner.serveSocket.user.vo.UserVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface AuthMapper {
	
    SecUser getSecUserInfoByUserName(String username);

    List<SecRole> getSecRoleListByUserName(String username);
    
    int updateSecUser(SecUser secUser);
    
    public int insertSecUser(SecUser secUser);
    
    int insertSecUserSecRole(SecUser secUser);
    
    public int changePassword(SecUser secUser);
    
    public int changeExamPassword(HashMap<String, String> map);
    
    public UserVO existUser(HashMap<String, String> map);
    
    public SecUser accountCondition(SecUser secUser);
    
    public int updateAccountCondition(SecUser secUser);
}
