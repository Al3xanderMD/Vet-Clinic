package com.example.MyVet;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@OpenAPIDefinition
public class MyVetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyVetApplication.class, args);
	}

}
