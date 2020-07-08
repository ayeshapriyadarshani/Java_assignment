package com.wiley.covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.wiley.covid19.repository")
public class RestBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBootApplication.class, args);
	}

}
