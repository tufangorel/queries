package com.polygons.queries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCircuitBreaker
public class QueriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueriesApplication.class, args);
	}
	
}