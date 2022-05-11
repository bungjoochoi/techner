package kr.co.techner.serveSocket.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static String getPasswordEncoder(String pwd) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(pwd);
	}
	
	public static void main(String[] args) {
		System.out.println(PasswordEncoder.getPasswordEncoder("12345678"));
	}

}
