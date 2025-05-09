package com.example.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class JobApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}

	@Autowired
	private JobRepository repository;
	@Bean
	ApplicationRunner init() {
		return (args) -> {
			// save
			repository.save(new Job("IT Support", true));
			repository.save(new Job("HR Assistant", false));
			repository.save(new Job("Marketing Analyst", true));
			// fetch
			repository.findAll().forEach(System.out::println);
		};
	}

}
