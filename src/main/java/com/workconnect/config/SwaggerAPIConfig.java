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

@Configuration
public class SwaggerAPIConfig {

    @Value("${jobhub.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Open API Dev Server");

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

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}
