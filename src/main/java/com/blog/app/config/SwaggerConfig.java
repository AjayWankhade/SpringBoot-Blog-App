package com.blog.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Blog App Backend",
        version = "1.0.0",
        description = "Project developed by Ajay Wankhade",
        contact = @Contact(
            name = "Ajay Wankhade",
            url = "https://my-portfolio-phi-navy-73.vercel.app/"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8888", description = "Local Development Server"),
        @Server(url = "https://your-live-api.com", description = "Production Server")
    }
)
@SecurityScheme(
    name = "BearerAuth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT"
)
@SecurityRequirement(name = "BearerAuth")
public class SwaggerConfig {
}
