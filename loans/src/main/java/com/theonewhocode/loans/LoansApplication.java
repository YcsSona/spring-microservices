package com.theonewhocode.loans;

import com.theonewhocode.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "EazyBank Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Sample User",
                        email = "email@email.com",
                        url = "https://www.google.com/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.google.com/"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank Loans microservice REST API Documentation",
                url = "https://www.google.com/"
        )
)
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
