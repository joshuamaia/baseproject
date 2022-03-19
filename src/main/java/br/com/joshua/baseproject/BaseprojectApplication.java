package br.com.joshua.baseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Base Project API", version = "1.0", description = "Base Project Information"))
public class BaseprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseprojectApplication.class, args);
	}

}
