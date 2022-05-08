package kr.co.biztechpartners.serveSocket.common.util.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.co.biztechpartners.serveSocket.common.file.service.FileStorageService;
import kr.co.biztechpartners.serveSocket.common.util.ProdDev;
import kr.co.biztechpartners.serveSocket.common.util.repository.SendMMSMapper;

@Service
public class MessageSendService {
	private static final Logger log = LoggerFactory.getLogger(MessageSendService.class);
	
	@Autowired
	ProdDev prodDev;
	
	@Autowired
	FileStorageService fileStorageService;
		
	@Autowired
	VelocityEngine velocityEngine;
	
	@Autowired
	SendMMSMapper sendMMSMapper;

	public String geContentFromTemplate(String templateName,Map<String, Object> model) {
		VelocityContext context = new VelocityContext(model);
		StringWriter stringWriter = new StringWriter();
		try {
			velocityEngine.mergeTemplate("/templates/"+templateName, "UTF-8", context, stringWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String text = stringWriter.toString();
		return text;
	}

	public void sendMail(String msg,String to , String subject) {
		
		final String from = "serveSocket.0@gmail.com";
		final String password = "!bne889900!!";
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.ssl.checkserveridentity", "false");
		props.put("mail.smtp.ssl.trust", "*");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		
		try {
			session.setDebug(true);
			Transport transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			MimeMessage message = new MimeMessage(session);
			
			message.setSender(addressFrom);
			message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
			message.setSubject(MimeUtility.encodeText(subject, "EUC-KR", "B"));
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			//각각의 파일들을 저장시켜주기 위한 경로
			ArrayList<Resource> fileList = new ArrayList<>();
			
			if(msg.indexOf("<img") != -1) {
				// 추가
				String htmlText = "cid:image";
				
				//정규표현식으로 img src 부분을 가져옴.
				Pattern nonValidPattern = Pattern
						.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
				Matcher matcher = nonValidPattern.matcher(msg);
				
				Resource filePath;
				String tmpFilePath;
				int i = 0;
				while (matcher.find()) {
					
					tmpFilePath = msg.substring(msg.indexOf("/img"), msg.indexOf("/img")+48);
					// img 끝자리가 jpeg 일 경우 
					if(tmpFilePath.endsWith("jpe")) {
						tmpFilePath = msg.substring(msg.indexOf("/img"), msg.indexOf("/img")+49);
					} else {
						tmpFilePath = msg.substring(msg.indexOf("/img"), msg.indexOf("/img")+48);
					}
					filePath = fileStorageService.loadImgFileAsResource(tmpFilePath);
					fileList.add(filePath);
					
					msg = msg.replace(msg.substring(msg.indexOf("/viewImgAttach"), msg.indexOf("/viewImgAttach")+62), htmlText+i);
					i++;
				}
				messageBodyPart.setContent(msg,"text/html; charset=UTF-8");
				// add it
				multipart.addBodyPart(messageBodyPart);
				DataSource fds;
				// 본문에 이미지가 여러개일 경우
				for(int j =0 ; j<fileList.size(); j++) {
					// second part (the image)
					messageBodyPart = new MimeBodyPart();
					fds = new FileDataSource(fileList.get(j).getFile());
					messageBodyPart.setDataHandler(new DataHandler(fds));
					messageBodyPart.setHeader("Content-ID","<image"+j+">");
					multipart.addBodyPart(messageBodyPart);
				}
				
				//---- 끝
			} else {
				messageBodyPart.setContent(msg, "text/html; charset=UTF-8");
				// add it viewImgAttach
				multipart.addBodyPart(messageBodyPart);
			}
				
			message.setContent(multipart);
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			
			if(prodDev.isProd()) {
				if (!transport.isConnected())
					transport.connect();
				
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("prod mail");
			} else {
				System.out.println("dev mail");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean createSendMMS(Map<String, Object> sms) {
		return sendMMSMapper.insertSendMMS(sms) > 0;
	}

}