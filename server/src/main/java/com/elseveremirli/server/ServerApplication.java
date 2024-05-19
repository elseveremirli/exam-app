package com.elseveremirli.server;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}


	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Contact Application API")
						.description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
	}
}
