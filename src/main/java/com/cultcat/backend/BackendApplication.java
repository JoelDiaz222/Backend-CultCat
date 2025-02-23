package com.cultcat.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.setProperty("DB_URL", Dotenv.load().get("DB_URL"));
		SpringApplication.run(BackendApplication.class, args);
	}

}
