package kr.co.techner.serveSocket.serveSocket.service;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.techner.serveSocket.serveSocket.repository.ServeSocketMapper;
import kr.co.techner.serveSocket.serveSocket.vo.ServeSocket;

@Service
public class ServeSocketService {

	private static final Logger logger = Logger.getLogger(ServeSocketService.class.getName());
	private static FileHandler fh = null;
    @Autowired
    ServeSocketMapper serveSocketMapper;
    
    public List<ServeSocket> serveSocketListAjax(ServeSocket serveSocket) {
    	return serveSocketMapper.serveSocketListAjax(serveSocket);
    }
}	
