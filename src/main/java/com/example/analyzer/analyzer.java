package com.example.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class analyzer {

	public static void main(String[] args) {
		SpringApplication.run(analyzer.class, args);
	}
}
