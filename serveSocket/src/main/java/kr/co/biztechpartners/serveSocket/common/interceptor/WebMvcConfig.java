package kr.co.biztechpartners.serveSocket.common.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.biztechpartners.serveSocket.common.security.HTMLCharacterEscapes;
import kr.co.biztechpartners.serveSocket.common.security.xss.CrossScriptingFilter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
    public LogInterceptor logInterceptor;
	@Autowired
	public SessionInterceptor sesInterceptor;
	@Autowired
	public SecurityInterceptor secInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sesInterceptor).addPathPatterns("/**").excludePathPatterns("/login/**");
		
		registry.addInterceptor(secInterceptor).addPathPatterns("/login/**");
		
		registry.addInterceptor(logInterceptor)
		.addPathPatterns("/admin/regist*")
		.addPathPatterns("/admin/delete*")
		.addPathPatterns("/company/modify*")
		.addPathPatterns("/company/create*")
		.addPathPatterns("/user/modify*")
		.addPathPatterns("/user/create*")
		.addPathPatterns("/user/remove*")
		.excludePathPatterns("/login/**");
		
	}
	


	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(escapingConverter());

    }

    @Bean
    public HttpMessageConverter escapingConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

        MappingJackson2HttpMessageConverter escapingConverter =
                new MappingJackson2HttpMessageConverter();
        escapingConverter.setObjectMapper(objectMapper);

        return escapingConverter;
    }

}
