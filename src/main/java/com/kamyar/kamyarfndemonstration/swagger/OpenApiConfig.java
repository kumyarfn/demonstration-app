package com.kamyar.kamyarfndemonstration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import static com.kamyar.kamyarfndemonstration.enums.Constants.BEARER_AUTH;

@Configuration
@OpenAPIDefinition
        (info = @Info(title = "Web Application Demonstration"
                     ,contact = @Contact(name = "Kamyar Farzanenia"
                     ,email = "komyar.fn@gmail.com")
                     ,version = "0.0.1-SNAPSHOT"),
        security = @SecurityRequirement(name = "Token"))
@SecurityScheme(name = BEARER_AUTH, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@SecurityRequirement(name = BEARER_AUTH)
public class OpenApiConfig {

}
