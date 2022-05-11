package kr.co.techner.serveSocket.common.file.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BizFileNotFoundException extends RuntimeException{
	
	public BizFileNotFoundException(String message) {
		super(message);
	}
	
	public BizFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
