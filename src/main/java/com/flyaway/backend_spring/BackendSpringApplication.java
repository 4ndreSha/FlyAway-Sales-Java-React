package com.flyaway.backend_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.flyaway.backend_spring.entity")
public class BackendSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendSpringApplication.class, args);
	}
}