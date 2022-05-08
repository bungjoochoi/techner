package kr.co.biztechpartners.serveSocket.serveSocket.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.biztechpartners.serveSocket.serveSocket.vo.ServeSocket;

@Mapper
@Repository
public interface ServeSocketMapper {

    public List<ServeSocket> serveSocketListAjax(ServeSocket serveSocket);

}
