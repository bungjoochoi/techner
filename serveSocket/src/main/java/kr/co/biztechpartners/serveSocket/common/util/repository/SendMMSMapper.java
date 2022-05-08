package kr.co.biztechpartners.serveSocket.common.util.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface SendMMSMapper {

	int insertSendMMS(Map<String, Object> sms);
	
}
