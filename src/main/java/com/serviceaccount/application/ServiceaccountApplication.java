package com.serviceaccount.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages={
		"com.serviceaccount.security.repo", "com.serviceaccount.security.config","com.serviceaccount.security.service"})
@EnableAuthorizationServer
@EnableResourceServer
@EnableTransactionManagement
@EntityScan(basePackages = "com.serviceaccount.model")
@ComponentScan(basePackages = "com.serviceaccount.controller")
@EnableJpaRepositories(basePackages = "com.serviceaccount.repo")
@ComponentScan(basePackages = "com.serviceaccount.service")
public class ServiceaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceaccountApplication.class, args);
	}

}
