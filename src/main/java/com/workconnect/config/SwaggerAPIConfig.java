package com.workconnect.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {

    @Value("${jobhub.openapi.dev-url}")
    private String devUrl;

    @Value("${jobhub.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Open API Dev Server");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Open API Dev Server");

        //Info de contacto
    Contact contact = new Contact();
    contact.setEmail("jimena.qn26@gmail.com");
    contact.setName("JobHub");
    contact.setUrl("https://jobhub-upc.github.io/landing-page/");

    License mitlicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

    //Info general
        Info info = new Info()
                .title("JobHub API")
                .version("1.0")
                .contact(contact)
                .description("Esta API muestra los endpoints para la aplicación JobHub")
                .termsOfService("")
                .license(mitlicense);

        // Configuración de seguridad JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT Authentication");

        Components components = new Components()
                .addSecuritySchemes("bearerAuth", securityScheme);

        // Requerimiento de seguridad para utilizar en las operaciones
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
