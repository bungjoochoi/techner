package kr.co.techner.serveSocket;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import kr.co.techner.serveSocket.common.file.property.FileStorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
	FileStorageProperties.class
})


public class ServeSocketApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServeSocketApplication.class);
    }
    
    
    @Bean
    public HttpSessionListener httpSessionListener(){
      return new SessionListener();
   }
    

    public static void main(String[] args) {

        SpringApplication.run(ServeSocketApplication.class, args);
    }

    public class SessionListener implements HttpSessionListener {
        @Override
        public void sessionCreated(HttpSessionEvent se) {
            se.getSession().setMaxInactiveInterval(60*60*8); //세션만료60분*8
        }
        @Override
        public void sessionDestroyed(HttpSessionEvent se) {
        }
    }


    
}
