package edu.kh.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class) // 보안 관련 자동설정 사용X
//@EnableWebSocketSecurity // 보안 관련 자동설정 사용O
// Spring Boot Application을 만들고 수행하는 데 필요한 필수 어노테이션을 묶어둔 어노테이션
public class BoardProjectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectBootApplication.class, args);
	}
}