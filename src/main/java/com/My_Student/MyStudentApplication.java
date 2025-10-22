package com.My_Student;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "My Student microservice REST API Documentation",
                description = "My Student) microservice REST API Documentation",
                version = "v1",
                contact = @Contact  (
                        name = "Kehinde Raji",
                        email = "kerahnTech@gmail.com",
                        url = "https://www.mystudent.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.mystudent.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "My Student microservice REST API Documentation",
                url = "https://www.mystudent.com"
        )
)
public class MyStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyStudentApplication.class, args);
	}

}
