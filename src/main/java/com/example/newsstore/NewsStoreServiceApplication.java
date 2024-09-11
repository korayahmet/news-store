package com.example.newsstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.newsstore.model") // specify the package where your entities are located
public class NewsStoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsStoreServiceApplication.class, args);
	}
}
