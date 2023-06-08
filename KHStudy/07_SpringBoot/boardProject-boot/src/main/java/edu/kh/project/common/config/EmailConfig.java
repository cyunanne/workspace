package edu.kh.project.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import oracle.net.aso.j;

@Configuration
@PropertySource("classpath:/config.properties")
public class EmailConfig {
	
	// @value : properties 파일에서 키가 일치하는 부분의 value를 얻어와 대입
	@Value("${spring.mail.username}")
	private String userName;
	@Value("${spring.mail.password}")
	private String password;
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setUsername(userName);
		javaMailSenderImpl.setPassword(password);
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		
		Properties prop = new Properties();
		
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.debug", "true");
		prop.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		
		javaMailSenderImpl.setJavaMailProperties(prop);
		
		return javaMailSenderImpl;
	}

}
