package com.example.SistemaGIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@SpringBootApplication
public class SistemaGisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaGisApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	BasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthEntryPoint.setRealmName("SistemaGIS");
		return basicAuthEntryPoint;
	}

}
