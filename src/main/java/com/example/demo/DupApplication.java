package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class DupApplication {

	public static void main(String[] args) {
		SpringApplication.run(DupApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect(){
		return new SpringDataDialect();
	}
}
