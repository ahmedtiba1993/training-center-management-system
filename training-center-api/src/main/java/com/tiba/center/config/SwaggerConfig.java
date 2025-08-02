package com.tiba.center.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Training Center API",
            version = "1.0",
            description = "API documentation for Training Center Management System",
            contact =
                @Contact(
                    name = "Ahmed Tiba",
                    email = "ahmed.tiba.1993@gmail.com",
                    url = "https://ahmedtiba1993.github.io")))
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {}
