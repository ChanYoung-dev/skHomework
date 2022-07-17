package com.example.demo;

import com.example.demo.UserAccount.Service.UserAccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public TestDataInit testDataInit(UserAccountService userAccountService) {
		return new TestDataInit(userAccountService);
	}
}
